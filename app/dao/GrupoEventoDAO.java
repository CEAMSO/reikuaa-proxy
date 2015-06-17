package dao;

import models.GrupoEvento;
import models.TipoEvento;

/**
 * Created by gaby.lorely on 07/06/2015.
 */
public class GrupoEventoDAO {

    public GrupoEvento get(Long id){
        GrupoEvento e = GrupoEvento.find.byId(id);
        return e;
    }
}
