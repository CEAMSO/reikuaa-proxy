package dao;

import configurations.Parametro;
import play.libs.F;
import play.libs.WS;

import configurations.Parametro;
import play.libs.F;
import play.libs.WS;
import sun.misc.BASE64Encoder;

/**
 * Created by gaby.lorely on 22/05/2015.
 */

public class CapacitacionDAO {

    /**
     * Obtiene un llamado a la api interna de la DNCP para retornar las últimas
     *
     * @return la promesa del response del servicio base.
     */
    public final F.Promise<WS.Response> getUltimasCapacitaciones(String offset , String limit) {

        WS.WSRequestHolder holder = WS.url(Parametro.URL_BACKEND + Parametro.URL_CAPACITACIONES);
        WS.WSRequestHolder complexHolder = holder.setAuth(Parametro.USER_BACKEND, Parametro.PASS_BACKEND)
                .setQueryParameter("offset", offset)
                .setQueryParameter("limit", limit);
        return complexHolder.get();
    }
}
