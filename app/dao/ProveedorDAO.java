package dao;

import configurations.Parametro;
import play.libs.F;
import play.libs.WS;

/**
 * Created by alejandro on 24/05/15.
 */
public class ProveedorDAO {
    /**
     * Obtiene un llamado a la api interna de la DNCP para retornar el proveedor
     *
     * @return la promesa del response del servicio base.
     */
    public final F.Promise<WS.Response> getProveedor(String ruc) {

        WS.WSRequestHolder holder = WS.url(Parametro.URL_BACKEND + Parametro.URL_PROVEEDORES + "check-ruc/" + ruc);
        WS.WSRequestHolder complexHolder = holder.setAuth(Parametro.USER_BACKEND, Parametro.PASS_BACKEND);
//                .setQueryParameter("offset", offset)
//                .setQueryParameter("limit", limit);
        return complexHolder.get();
    }

    public final F.Promise<WS.Response> getCategoriasPorProveedor(String slug) {

        WS.WSRequestHolder holder = WS.url(Parametro.URL_BACKEND + Parametro.URL_PROVEEDORES + slug + "/categorias");
        WS.WSRequestHolder complexHolder = holder.setAuth(Parametro.USER_BACKEND, Parametro.PASS_BACKEND);
//                .setQueryParameter("offset", offset)
//                .setQueryParameter("limit", limit);
        return complexHolder.get();
    }

    public final F.Promise<WS.Response> getProductosPorProveedor(String slug) {

        WS.WSRequestHolder holder = WS.url(Parametro.URL_BACKEND + Parametro.URL_PROVEEDORES + slug + "/productos");
        WS.WSRequestHolder complexHolder = holder.setAuth(Parametro.USER_BACKEND, Parametro.PASS_BACKEND);
//                .setQueryParameter("offset", offset)
//                .setQueryParameter("limit", limit);
        return complexHolder.get();
    }

}

