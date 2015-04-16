package facade;

import dao.DispositivoDao;
import dao.SuscripcionDao;
import models.Dispositivo;
import models.Suscripcion;
import models.Usuario;
import py.gov.dncp.ws.framework.BaseFacade;

/**
 * Created by gaby.lorely on 22/03/2015.
 */
public class SuscripcionFacade extends BaseFacade {

    SuscripcionDao dao = getInstance(SuscripcionDao.class);

    UsuarioFacade usuarioFacade = getInstance(UsuarioFacade.class);

    /**
     * Registra una suscripción de un usuario a una planificacion
     * @param planificacionId id de la planificacion
     * @param mail mail del dispositivo a suscribir
     * @return
     * @throws Exception
     */
    public Suscripcion registrar( String mail, String planificacionId) throws Exception{
        Usuario user = usuarioFacade.getByMail(mail);

        if(dao.get(user, planificacionId) == null) {
            Suscripcion suscripcion = new Suscripcion();
            suscripcion.setPlanificacionId(planificacionId);
            suscripcion.setUsuario(user);

            return dao.persist(suscripcion);
        }
        return null;
    }

    /**
     * Elimina una suscripción de un usuario a una planificacion
     * @param planificacionId id de la planificacion
     * @param mail mail del dispositivo a suscribir
     * @return
     * @throws Exception
     */
    public void eliminar( String mail, String planificacionId) throws Exception{
        Usuario user = usuarioFacade.getByMail(mail);
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setPlanificacionId(planificacionId);
        suscripcion.setUsuario(user);

        dao.delete(suscripcion);
    }
}
