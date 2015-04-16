package facade;

import dao.UsuarioDAO;
import models.Dispositivo;
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
        Usuario user = dao.getByMail(dispositivo.getMail());
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
            user.setMail(dispositivo.getMail());
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
        user.setMail(dispositivo.getMail());
        if(dao.tieneDispositivo(user,dispositivo)){
            dispositivoFacade.eliminar(dispositivo);
        }
    }


    /**
     * Retorna un usuario que coincida con el mail que se recibe como
     * parámetro o null en caso de que no se encuentre uno.
     * @param mail
     * @return
     */
    public Usuario getByMail(String mail)throws Exception { return dao.getByMail(mail);}

    /**
     * Actualizar el registration id del Dispositivo correspondiente
     * @param dispositivo
     * @param registrationId
     */
    public void actualizarRegistrationId(Dispositivo dispositivo, String registrationId) throws Exception{
        Usuario user = new Usuario();
        user.setMail(dispositivo.getMail());
        Dispositivo d = new Dispositivo();
        d.setRegistrationId(registrationId);
        d.setTipoDispositivo(dispositivo.getTipoDispositivo());
        if(dao.tieneDispositivo(user,d)){
           Dispositivo dd = dispositivoFacade.find(dispositivo.getMail(), registrationId, dispositivo.getTipoDispositivo());
           dd.setRegistrationId(dispositivo.getRegistrationId());
           dispositivoFacade.update(dd);
        }
    }
}
