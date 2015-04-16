package autenticacion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import dao.DispositivoDao;
import models.Dispositivo;
import models.Usuario;
import play.mvc.Http;
import play.mvc.Security;

import static play.mvc.Controller.flash;

/**
 * Autenticación básica de http
 * Created by gaby.lorely on 07/03/2015.
 */
public class BasicAuthenticator extends Security.Authenticator {

    private static final String AUTHORIZATION = "authorization";


    @Override
    public String getUsername(Http.Context ctx) {
        Map<String,Object> header = getAuthenticationParams(ctx);
        System.out.println("AUTENTICANDO DISPOSITIVO PETICION " + ctx.request().uri() + " " + ctx.request().method());

        if (header != null) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put(Dispositivo.Field.USUARIO + "." + Dispositivo.Field.MAIL , header.get("usuario"));
            map.put(Dispositivo.Field.REGISTRATION_ID, header.get("password"));

            Dispositivo dispositivo = Dispositivo.find.where().allEq(map).findUnique();
            System.out.println("Dispositivo encontrado: " + dispositivo);
            if (dispositivo != null) {
                flash(Usuario.Field.MAIL, (String)header.get("usuario"));
                flash(Dispositivo.Field.REGISTRATION_ID, (String) header.get("password"));
                System.out.println("RETORNANDO BASIC AUTHENTICATOR");
                return (String)header.get("usuario");
            }
        }
        return null;
    }



   /* @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }*/

    protected Map<String,Object> getAuthenticationParams(Http.Context context) {
        String authHeader = context.request().getHeader(AUTHORIZATION);
        if (authHeader == null) {
            return null;
        }

        String auth = authHeader.substring(6);
        byte[] decodedAuth = new byte[0];
        try {
            decodedAuth = new sun.misc.BASE64Decoder().decodeBuffer(auth);
            String[] credString = new String(decodedAuth, "UTF-8").split(":");
            if (credString == null || credString.length != 2) {
                return null;
            }

            String username = credString[0];
            String password = credString[1];
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("password", password);
            map.put("usuario", username);
            System.out.println("CODIGO: " + password);
            System.out.println("MAIL: " + username);
            return map;

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
