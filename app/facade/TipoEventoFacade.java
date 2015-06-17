package facade;

import dao.TipoEventoDAO;
import models.TipoEvento;
import py.gov.dncp.ws.exception.FacadeException;
import py.gov.dncp.ws.framework.BaseFacade;

/**
 * Created by gaby.lorely on 19/04/2015.
 */
public class TipoEventoFacade  extends BaseFacade {

    TipoEventoDAO dao = getInstance(TipoEventoDAO.class);

    /**
     * Obtiene un TipoEvento por su ID
     * @param id
     * @return
     * @throws FacadeException
     */
    public TipoEvento getById(Long id) throws FacadeException{
        return dao.get(id);
    }
}
