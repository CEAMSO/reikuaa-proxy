package facade;

import dao.GrupoEventoDAO;
import models.GrupoEvento;
import py.gov.dncp.ws.framework.BaseFacade;

/**
 * Created by gaby.lorely on 07/06/2015.
 */
public class GrupoEventoFacade extends BaseFacade {

    GrupoEventoDAO dao = getInstance(GrupoEventoDAO.class);

    public GrupoEvento get(Long id) {
        return dao.get(id);
    }
}
