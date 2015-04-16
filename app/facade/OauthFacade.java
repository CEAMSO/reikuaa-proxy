package facade;

import dao.OauthDao;
import play.libs.F;
import play.libs.WS;
import py.gov.dncp.ws.framework.BaseFacade;
import py.gov.dncp.ws.framework.FacadeResponse;
import py.gov.dncp.ws.exception.FacadeException;
/**
 * Created by gaby.lorely on 08/03/2015.
 */
public class OauthFacade extends BaseFacade {

    OauthDao dao = getInstance(OauthDao.class);

    /**
     * Obtiene el access token requerido por un telefono registrado
     *
     * @return una promesa del response
     */
    public F.Promise<FacadeResponse> getAccessToken() {
        final F.Promise<WS.Response> llamadoPromise = dao.getOauthAccessToken();

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
