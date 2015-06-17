package dao;

import com.fasterxml.jackson.databind.JsonNode;

import configurations.Parametro;
import models.notificaciones.AndroidNotification;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;

/**
 * Acceso a api de GCM
 * Created by gaby.lorely on 19/04/2015.
 */
public class NotificacionDAO {

    /**
     * Obtiene un llamado a la api de GCM
     *
     * @return la promesa del response del servicio base.
     */
    public final F.Promise<WS.Response> postAndroidNotification(AndroidNotification notificacion) {

        WS.WSRequestHolder holder = WS.url(Parametro.URL_GCM).setContentType("application/json");
        WS.WSRequestHolder complexHolder = holder.setHeader("Authorization", "key=" + Parametro.API_KEY);
        JsonNode json = Json.toJson(notificacion);
        System.out.println("Json a Enviar: " + String.valueOf(json));
        return complexHolder.post(json);
    }
}
