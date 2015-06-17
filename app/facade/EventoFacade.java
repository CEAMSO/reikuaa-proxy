package facade;

import java.util.List;

import configurations.Parametro;
import dao.EventoDAO;
import models.Evento;
import models.TipoEvento;
import py.gov.dncp.ws.exception.FacadeException;
import py.gov.dncp.ws.framework.BaseFacade;

/**
 * Created by gaby.lorely on 19/04/2015.
 */
public class EventoFacade extends BaseFacade {

    TipoEventoFacade tipoEventoFacade = getInstance(TipoEventoFacade.class);

    EventoDAO dao = getInstance(EventoDAO.class);

    /**
     * Registra un nuevo evento y lanza el proceso de notificación
     *
     * @param evento
     * @throws FacadeException
     */
    public void registrar(Evento evento) throws FacadeException {

        TipoEvento tipo = tipoEventoFacade.getById(evento.getTipoEventoId());

        if (tipo != null) {
            play.Logger.info("Tipo Evento: " + tipo.getNombre());
            if(evento.getPlanificacionId()== null){
                evento.setPlanificacionId(Parametro.NOTIFICACION_GENERAL);
            }
            play.Logger.info("Licitacion: " + evento.getPlanificacionId());
            evento.setTipoEvento(tipo);
            evento = dao.persist(evento);

        } else {
            throw new FacadeException("Tipo Evento no Encontrado");
        }
    }

    /**
     * Retorna la lista de eventos que aun no fueron notificados
     * @return
     */
    public List<Evento> getEventosSinNotificar() {
        return dao.getSinNotificar();

    }

    public Evento update(Evento evento) {
        return dao.update(evento);
    }
}
