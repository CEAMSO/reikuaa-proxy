package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.EventoUsuario;
import models.Usuario;

/**
 * Created by alejandro on 28/03/15.
 */
public class EventoUsuarioDAO {

    public EventoUsuario persist (EventoUsuario eventoUsuario){
        eventoUsuario.save();
        return eventoUsuario;
    }

    public void delete (EventoUsuario eventoUsuario){

        Map<String, Object> map = new HashMap<String,Object>();

//        map.put(EventoUsuario.Field.ID, eventoUsuario.getUsuario().getId());
//        map.put(EventoUsuario.Field.TIPOEVENTO, eventoUsuario.getTipoEvento().getId());
        map.put(EventoUsuario.Field.USUARIO + "." + Usuario.Field.ID, eventoUsuario.getUsuario().getId());
        List<EventoUsuario> eventos = EventoUsuario.find.where().allEq(map).findList();
        for (EventoUsuario evento : eventos) {
            if (evento != null)
                evento.delete();
        }
    }
}
