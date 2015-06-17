import facade.NotificacionFacade;
import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

/**
 *Carga datos iniciales de archivos yml
 * - Tipo de Evento
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        super.onStart(app);
        System.out.println("LLAMADA A ON START");
        //Carga de base de datos
        InitialData.insert(app);

        //Calendarización de eventos
        NotificacionFacade notificaciones = new NotificacionFacade();
        notificaciones.calendarizarNotificaciones();
    }

    static class InitialData {

        public static void insert(Application app) {

            if(Ebean.find(GrupoEvento.class).findRowCount() == 0) {

                Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");

                // Insert users first
                Ebean.save(all.get("grupoEventos"));

            }

            if(Ebean.find(TipoEvento.class).findRowCount() == 0) {

                Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");

                // Insert users first
                Ebean.save(all.get("tipoEventos"));

            }
        }

    }

}