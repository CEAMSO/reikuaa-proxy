package facade;

import java.util.List;

import configurations.Parametro;
import dao.UsuarioDAO;
import models.Dispositivo;
import models.GrupoEvento;
import models.TipoEvento;
import models.Usuario;
import py.gov.dncp.ws.framework.BaseFacade;

/**
 * Created by alejandro on 28/03/15.
 */
public class UsuarioFacade extends BaseFacade {

    UsuarioDAO dao = getInstance(UsuarioDAO.class);
    DispositivoFacade dispositivoFacade = getInstance(DispositivoFacade.class);

    /**
     * Registra una entidad Usuario.
     * Si ya existe un usuario con el mismo mail, verifica que no exista un registration id para
     * el dispositivo y agrega un nuevo dispositivo. Si no existe el usuario, crea el usuario
     * y agrega un dispositivo con el registration id correspondiente.
     * @param dispositivo entidad Dispositivo
     * @return
     * @throws Exception
     */
    public Usuario registrar(Dispositivo dispositivo) throws Exception{
        Usuario user = dao.getByIdentificador(dispositivo.getIdentificador());
        if(user != null) {
            //Actualizar datos de dispositivo
            if (!dao.tieneDispositivo(user, dispositivo)) {
                dispositivo.setUsuario(user);
                user.getDispositivos().add(dispositivo);
                dao.update(user);
            }
        }else{
            //Guardar el usuario y su dispositivo
            user = new Usuario();
            user.getDispositivos().add(dispositivo);
            user.setIdentificador(dispositivo.getIdentificador());
            dispositivo.setUsuario(user);
            dao.save(user);
        }
        return user;
    }

    /**
     * Elimina una entidad dispositivo que se corresponde con un usuario
     * @param dispositivo entidad Dispositivo
     * @return
     * @throws Exception
     */
    public void eliminarDispositivo(Dispositivo dispositivo) throws Exception{
        Usuario user = new Usuario();
        user.setIdentificador(dispositivo.getIdentificador());
        if(dao.tieneDispositivo(user,dispositivo)){
            dispositivoFacade.eliminar(dispositivo);
        }
    }


    /**
     * Retorna un usuario que coincida con el identificador que se recibe como
     * parámetro o null en caso de que no se encuentre uno.
     * @param identificador
     * @return
     */
    public Usuario getByIdentificador(String identificador)throws Exception { return dao.getByIdentificador(identificador);}

    /**
     * Actualizar el registration id del Dispositivo correspondiente
     * @param dispositivo
     * @param registrationId
     */
    public void actualizarRegistrationId(Dispositivo dispositivo, String registrationId) throws Exception{
        Usuario user = new Usuario();
        user.setIdentificador(dispositivo.getIdentificador());
        Dispositivo d = new Dispositivo();
        d.setRegistrationId(registrationId);
        d.setTipoDispositivo(dispositivo.getTipoDispositivo());
        if(dao.tieneDispositivo(user,d)){
           Dispositivo dd = dispositivoFacade.find(dispositivo.getIdentificador(), registrationId, dispositivo.getTipoDispositivo());
           dd.setRegistrationId(dispositivo.getRegistrationId());
           dispositivoFacade.update(dd);
        }
    }

    /**
     * Retorna la lista de usuarios suscriptos a un grupo evento
     * @param GrupoEvento
     * @return
     */
    public List<Usuario> getSuscriptos(GrupoEvento grupo, String planificacionId, String tipoDispositivo){
        return dao.findSuscriptos(grupo, planificacionId, tipoDispositivo);
    }

    public List<Usuario> getAllAndroid() {
        return dao.getByTipoDispositivo(Parametro.TIPO_ANDROID);
    }
}
