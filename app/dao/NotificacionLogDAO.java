package dao;

import models.Evento;
import models.NotificacionLog;

/**
 * Acceso a datos para el modelo NotificacionLog
 * Created by gaby.lorely on 07/06/2015.
 */
public class NotificacionLogDAO {
    public NotificacionLog persist (NotificacionLog log){
        log.save();
        return log;
    }

}
