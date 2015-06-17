package dao;

import models.TipoEvento;

/**
 * Acceso a datos para TipoEvento
 * Created by gaby.lorely on 19/04/2015.
 */
public class TipoEventoDAO {

    public TipoEvento get(Long id){
        TipoEvento e = TipoEvento.find.byId(id);
        return e;
    }
}
