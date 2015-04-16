package facade;

import java.util.List;

import dao.EventoUsuarioDAO;
import models.EventoUsuario;
import models.TipoEvento;
import models.Usuario;
import py.gov.dncp.ws.framework.BaseFacade;

/**
 * Created by alejandro on 28/03/15.
 */
public class EventoUsuarioFacade extends BaseFacade {

    EventoUsuarioDAO dao = getInstance(EventoUsuarioDAO.class);

    UsuarioFacade usuarioFacade = getInstance(UsuarioFacade.class);

    /**
     *
     * @param mail
     * @param tiposEventos
     * @return
     * @throws Exception
     */
    public void registrar( String mail, List<TipoEvento> tiposEventos) throws Exception{
        Usuario usuario = usuarioFacade.getByMail(mail);
        EventoUsuario eventoUsuarioABorrar = new EventoUsuario();
        eventoUsuarioABorrar.setUsuario(usuario);
        dao.delete(eventoUsuarioABorrar);

        for ( TipoEvento tipoEvento : tiposEventos ) {
            EventoUsuario eventoUsuario = new EventoUsuario();
            eventoUsuario.setUsuario(usuario);
            eventoUsuario.setTipoEvento(tipoEvento);
            dao.persist(eventoUsuario);
        }
    }
}
