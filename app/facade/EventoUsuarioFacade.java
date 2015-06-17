package facade;

import java.util.List;

import dao.EventoUsuarioDAO;
import dao.TipoEventoDAO;
import models.EventoUsuario;
import models.GrupoEvento;
import models.TipoEvento;
import models.Usuario;
import py.gov.dncp.ws.framework.BaseFacade;

/**
 * Created by alejandro on 28/03/15.
 */
public class EventoUsuarioFacade extends BaseFacade {

    EventoUsuarioDAO dao = getInstance(EventoUsuarioDAO.class);

    UsuarioFacade usuarioFacade = getInstance(UsuarioFacade.class);

    GrupoEventoFacade grupoEventoDAO = getInstance(GrupoEventoFacade.class);

    /**
     *
     * @param identificador
     * @param gruposEventos
     * @return
     * @throws Exception
     */
    public void registrar( String identificador, List<GrupoEvento> gruposEventos) throws Exception{
        Usuario usuario = usuarioFacade.getByIdentificador(identificador);
        EventoUsuario eventoUsuarioABorrar = new EventoUsuario();
        eventoUsuarioABorrar.setUsuario(usuario);
        dao.delete(eventoUsuarioABorrar);
        for ( GrupoEvento grupo : gruposEventos ) {
            EventoUsuario eventoUsuario = new EventoUsuario();
            grupo = grupoEventoDAO.get(grupo.getId());
            if(grupo != null) {
                eventoUsuario.setUsuario(usuario);
                eventoUsuario.setGrupoEvento(grupo);
                dao.persist(eventoUsuario);
            }
        }
    }
}
