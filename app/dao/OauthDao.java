package dao;

import configurations.Parametro;
import play.libs.F;
import play.libs.WS;

/**
 * Created by gaby.lorely on 08/03/2015.
 */
public class OauthDao {


    /**
     * Obtiene un llamado a la api para access token
     *
     * @return la promesa del response del servicio base.
     */
    public final F.Promise<WS.Response> getOauthAccessToken() {

        WS.WSRequestHolder holder = WS.url(Parametro.URL_CONTRATACIONES);
        WS.WSRequestHolder complexHolder = holder.setHeader("Authorization", Parametro.REQUEST_TOKEN);
        return complexHolder.post("");
    }
}
