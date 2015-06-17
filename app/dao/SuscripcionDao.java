package dao;

import java.util.HashMap;
import java.util.Map;

import models.Suscripcion;
import models.Usuario;

/**
 * Acceso a Datos para modelo de Suscripcion
 * Created by gaby.lorely on 22/03/2015.
 */
public class SuscripcionDao {


    public Suscripcion persist (Suscripcion suscripcion){
        suscripcion.save();
        return suscripcion;
    }

    /**
     * Trae la suscripción por usuario y planificación id y la elimina
     * @param suscripcion
     */
    public void delete (Suscripcion suscripcion){

        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Suscripcion.Field.USUARIO + "." + Usuario.Field.ID, suscripcion.getUsuario().getId());
        map.put(Suscripcion.Field.PLANIFICACION_ID , suscripcion.getPlanificacionId());

        Suscripcion susc = Suscripcion.find.where().allEq(map).findUnique();
        susc.delete();
    }

    /**
     * Obtiene una suscripción por usuario y planificación si existe. Si
     * no existe retorna null
     * @param user
     * @param planificacionId
     * @return
     */
    public Suscripcion get(Usuario user, String planificacionId) {
        Suscripcion s = null;
        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Suscripcion.Field.USUARIO + "." + Usuario.Field.ID, user.getId());
        map.put(Suscripcion.Field.PLANIFICACION_ID, planificacionId);

        s = Suscripcion.find.where().allEq(map).findUnique();

        return s;
    }
}
