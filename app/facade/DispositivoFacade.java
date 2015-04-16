package facade;

import dao.DispositivoDao;
import models.Dispositivo;
import py.gov.dncp.ws.framework.BaseFacade;

/**
 * Created by gaby.lorely on 08/03/2015.
 */
public class DispositivoFacade extends BaseFacade {

    DispositivoDao dao = getInstance(DispositivoDao.class);

    /**
     * Registra una entidad telefono
     * @param dispositivo entidad Telefono
     * @return
     * @throws Exception
     */
    public Dispositivo registrar(Dispositivo dispositivo) throws Exception{
        return dao.save(dispositivo);
    }

    /**
     * Elinina una entidad telefono para que pueda acceder a datos del proxy
     * @param dispositivo entidad Telefono
     * @return
     * @throws Exception
     */
    public void eliminar(Dispositivo dispositivo) throws Exception{
         dao.delete(dispositivo);
    }

    /**
     * Actualiza una entidad telefono para que pueda acceder a datos del proxy
     * @param dispositivo entidad Telefono
     * @return
     * @throws Exception
     */
    public Dispositivo actualizar(Dispositivo dispositivo) throws Exception{
        return dao.update(dispositivo);
    }

    public Dispositivo find(String mail, String registrationId, String tipoDispositivo){
        return dao.find(mail, registrationId, tipoDispositivo);
    }

    public Dispositivo find(String mail, String registrationId){
        return dao.find(mail, registrationId);
    }

    public void update(Dispositivo d) {
        dao.update(d);
    }
}
