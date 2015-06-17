package facade;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import akka.actor.Cancellable;
import configurations.Parametro;
import dao.DispositivoDao;
import dao.NotificacionDAO;
import dao.NotificacionLogDAO;
import models.Dispositivo;
import models.Evento;
import models.NotificacionLog;
import models.Usuario;
import models.notificaciones.AndroidNotification;
import models.notificaciones.AndroidResponse;
import models.notificaciones.Data;
import models.notificaciones.Result;
import play.libs.Akka;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;
import py.gov.dncp.ws.exception.FacadeException;
import py.gov.dncp.ws.framework.BaseFacade;
import scala.concurrent.duration.Duration;

/**
 * Created by gaby.lorely on 19/04/2015.
 */
public class NotificacionFacade extends BaseFacade {

    UsuarioFacade usuarioFacade = getInstance(UsuarioFacade.class);
    EventoFacade eventoFacade = getInstance(EventoFacade.class);
    NotificacionDAO dao = getInstance(NotificacionDAO.class);
    DispositivoDao dispositivoDao = getInstance(DispositivoDao.class);
    NotificacionLogDAO notificacionLogDao = getInstance(NotificacionLogDAO.class);

    public  void calendarizarNotificaciones(){
        play.Logger.info("Calendarizando proceso de notificaciones cada 1 minuto");
        Cancellable schedule = Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.MILLISECONDS),
                Duration.create(Parametro.CALENDARIO_NOTIFICACION, TimeUnit.SECONDS),
                new Runnable() {
                    @Override
                    public void run() {

                        comprobarEventos();
                    }
                },
                Akka.system().dispatcher()
        );
    }

    /**
     * Realiza la comprobación de eventos sin notificar y notifica
     */
    private void comprobarEventos() {
    List<Evento> eventos = eventoFacade.getEventosSinNotificar();
        if(!eventos.isEmpty()){
            //System.out.println("Eventos a notificar: " + eventos.size());
            for(Evento evento: eventos){
                if(!evento.getNotificadoAndroid()) {
                    notificarAndroid(evento);
                }else{
                    if(!evento.getNotificacionIos()){
                        //TODO
                    }
                }
            }
        }
    }


    /**
     * Realiza el proceso de notificar a todos los dispositivos
     * relacionados con el evento lanzado
     * @param evento
     */
    public void notificarAndroid(Evento evento) {
        List<Usuario> usuarios = new ArrayList<>();
       //Traemos la lista de usuarios suscriptos al tipo de evento padre
       if(evento.getTipoEvento().getGrupo() != null) {
            usuarios = usuarioFacade.getSuscriptos(evento.getTipoEvento().getGrupo(), evento.getPlanificacionId(), Parametro.TIPO_ANDROID);
       }else{
           //TODO logica para enviar a todos los dispositivos
          usuarios = usuarioFacade.getAllAndroid();
       }

       if(!usuarios.isEmpty()) {
           //Creamos la notificación a mandar

           play.Logger.info("NOTIFICAR EVENTO: " + evento.getDescripcion());
           AndroidNotification notificacion = new AndroidNotification();
           Data data = new Data();
           data.setDetalle(evento.getDescripcion());
           data.setIdLlamado(evento.getPlanificacionId());
           if(evento.getTipoEvento().getGrupo()!= null) {
               data.setGrupoEvento(evento.getTipoEvento().getGrupo().getId());
           }
           data.setTitulo(evento.getTipoEvento().getNombre());

           //Agregamos el registration id de todos los dispositivos
           for (Usuario usuario : usuarios) {

               for (Dispositivo dispositivo : usuario.getDispositivos()) {

                   play.Logger.info("Notificar a android: " + usuario.getIdentificador());
                   notificacion.addRegistrationId(dispositivo.getRegistrationId());
                   notificacion.addMail(usuario.getIdentificador());

               }
           }
           notificacion.setData(data);
           this.postAndroidNotification(notificacion, evento);

       }else{
           //Actualizar evento
           evento.setNotificadoAndroid(true);
           eventoFacade.update(evento);
       }

    }



    public  F.Promise<JsonNode> postAndroidNotification(final AndroidNotification notificacion, final Evento evento){
        final F.Promise<WS.Response> llamadoPromise = dao.postAndroidNotification(notificacion);
        final ObjectMapper mapper = new ObjectMapper();
        play.Logger.info("Inicia el proceso de notificacion");
        F.Promise<JsonNode> resultadoPromise = llamadoPromise
                .map(new F.Function<WS.Response, JsonNode>() {
                    public JsonNode apply(final WS.Response response)
                            throws Throwable {
                        play.Logger.info("Response Status: " + response.getStatus());

                        switch (response.getStatus()) {
                            case 404:
                                System.out.println("NOT FOUND");
                                return null;
                            case 200:
                                JsonNode json = response.asJson();
                                play.Logger.info("Response-----: " + json);
                                try {
                                    AndroidResponse respuesta;
                                    respuesta = Json.fromJson(json, AndroidResponse.class);
                                    procesarAndroidResponse(respuesta, notificacion, evento);

                                    //Actualizar evento
                                    evento.setNotificadoAndroid(true);
                                    eventoFacade.update(evento);
                                } catch (Exception e) {
                                    play.Logger.error(e.getMessage());
                                    throw new FacadeException(
                                            "Error al procesar respuesta del servidor GCM");
                                }
                                return json;
                            default:
                                play.Logger.info("Response ERROR");
                                throw new FacadeException(
                                        "Respuesta inesperada del servidor");
                        }

                    }

                });
        return resultadoPromise;
    }

    private void procesarAndroidResponse(AndroidResponse respuesta, AndroidNotification notificacion, Evento evento) {

        if(respuesta.getResults() != null){
            Integer index =0;
            for(Result result : respuesta.getResults()){
                NotificacionLog log = new NotificacionLog();
                log.setFechaRegistro(new Date());
                log.setMessageId(result.getMessage_id());
                log.setRegistrationId(notificacion.getRegistration_ids().get(index));
                log.setEvento(evento);
                if(result.getError()!=null){
                    log.setError(result.getError());
                    //Procesar los errores
                    if(result.getError().equals(Parametro.NOT_REGISTER_ANDROID )){
                        System.out.println("Eliminando dispositivo no registrado: " + notificacion.getMails().get(index) );
                        Dispositivo d = dispositivoDao.find(notificacion.getMails().get(index),notificacion.getRegistration_ids().get(index));
                        if(d!=null){
                            dispositivoDao.delete(d);
                        }
                    }
                }
                notificacionLogDao.persist(log);
                index++;
            }
        }
    }


}
