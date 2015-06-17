package facade;

import configurations.Parametro;
import dao.CapacitacionDAO;
import play.libs.F;
import play.libs.WS;
import py.gov.dncp.ws.exception.FacadeException;
import py.gov.dncp.ws.framework.BaseFacade;
import py.gov.dncp.ws.framework.FacadeResponse;
import sun.misc.BASE64Encoder;

/**
 * Created by gaby.lorely on 22/05/2015.
 */
public class CapacitacionFacade extends BaseFacade {

    CapacitacionDAO dao = getInstance(CapacitacionDAO.class);

    /**
     * Obtiene el access token requerido por un telefono registrado
     *
     * @return una promesa del response
     */
    public F.Promise<FacadeResponse> getCapacitaciones(String offset, String limit) {
        if(offset == null){
            offset = "0";
        }
        if(limit == null){
            limit ="10";
        }
        final F.Promise<WS.Response> llamadoPromise = dao.getUltimasCapacitaciones(offset, limit);

        return llamadoPromise
                .map(new F.Function<WS.Response, FacadeResponse>() {
                    public FacadeResponse apply(final WS.Response response)
                            throws Throwable {
                        switch (response.getStatus()) {
                            case 404:
                                return new FacadeResponse(null);
                            case 200:
                               // play.Logger.info("Json de Respuesta Capacitaciones: " + response.asJson().textValue());
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
