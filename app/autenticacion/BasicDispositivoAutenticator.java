package autenticacion;

import com.google.inject.Inject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import configurations.Parametro;
import models.Dispositivo;
import play.mvc.Http;
import play.mvc.Security;
import session.Session;

import static play.mvc.Controller.flash;

/**
 * Autentica que un dispositivo a ser registrado tenga el código de la aplicación en la autenticación
 * Created by gaby.lorely on 14/03/2015.
 */
public class BasicDispositivoAutenticator extends BasicAuthenticator {

    @Inject
    Session sesion;

    private static final String AUTHORIZATION = "authorization";

    @Override
    public String getUsername(Http.Context ctx) {
        Map<String,Object> map = super.getAuthenticationParams(ctx);

        if (map != null) {

           if(map.get("password").equals(Parametro.CODIGO_APLICACION) && map.get("usuario")!= null){
                  return (String)map.get("usuario");
           }
        }
        return null;
    }
}
