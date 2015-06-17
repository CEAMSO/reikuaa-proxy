package facade;

import dao.CapacitacionDAO;
import dao.ProveedorDAO;
import play.libs.F;
import play.libs.WS;
import py.gov.dncp.ws.exception.FacadeException;
import py.gov.dncp.ws.framework.BaseFacade;
import py.gov.dncp.ws.framework.FacadeResponse;

/**
 * Created by alejandro on 24/05/15.
 */
public class ProveedorFacade extends BaseFacade {

    ProveedorDAO dao = getInstance(ProveedorDAO.class);

    /**
     * Obtiene el access token requerido por un telefono registrado
     *
     * @return una promesa del response
     */
    public F.Promise<FacadeResponse> getProveedor(String ruc) {
        final F.Promise<WS.Response> llamadoPromise = dao.getProveedor(ruc);

        return llamadoPromise
                .map(new F.Function<WS.Response, FacadeResponse>() {
                    public FacadeResponse apply(final WS.Response response)
                            throws Throwable {
                        switch (response.getStatus()) {
                            case 404:
                                return new FacadeResponse(null);
                            case 200:
                                return new FacadeResponse(
                                        response.asJson());
                            default:
                                throw new FacadeException(
                                        "Respuesta inesperada del servidor");
                        }

                    }

                });
    }


    public F.Promise<FacadeResponse> getCategoriasPorProveedor(String slug) {
        final F.Promise<WS.Response> llamadoPromise = dao.getCategoriasPorProveedor(slug);

        return llamadoPromise
                .map(new F.Function<WS.Response, FacadeResponse>() {
                    public FacadeResponse apply(final WS.Response response)
                            throws Throwable {
                        switch (response.getStatus()) {
                            case 404:
                                return new FacadeResponse(null);
                            case 200:
                                return new FacadeResponse(
                                        response.asJson());
                            default:
                                throw new FacadeException(
                                        "Respuesta inesperada del servidor");
                        }

                    }

                });
    }

    public F.Promise<FacadeResponse> getProductosPorProveedor(String slug) {
        final F.Promise<WS.Response> llamadoPromise = dao.getProductosPorProveedor(slug);

        return llamadoPromise
                .map(new F.Function<WS.Response, FacadeResponse>() {
                    public FacadeResponse apply(final WS.Response response)
                            throws Throwable {
                        switch (response.getStatus()) {
                            case 404:
                                return new FacadeResponse(null);
                            case 200:
                                return new FacadeResponse(
                                        response.asJson());
                            default:
                                throw new FacadeException(
                                        "Respuesta inesperada del servidor");
                        }

                    }

                });
    }

}
