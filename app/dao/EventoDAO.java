package dao;

import com.avaje.ebean.Expr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Evento;
import models.Usuario;

/**
 * Acceso a datos para Evento
 * Created by gaby.lorely on 19/04/2015.
 */
public class EventoDAO {


    public Evento persist (Evento evento){
        evento.save();
        return evento;
    }

    public List<Evento> getSinNotificar() {
        List<Evento> eventos = new ArrayList<Evento>();
        eventos = Evento.find.where().or(Expr.eq(Evento.Field.NOTIFICADO_ANDROID,false),Expr.eq(Evento.Field.NOTIFICADO_IOS,false)).findList();
        return eventos;
    }

    public Evento update(Evento evento) {
        evento.update();
        return evento;
    }
}
