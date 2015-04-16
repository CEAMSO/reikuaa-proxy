package controllers;

import autenticacion.BasicAuthenticator;
import facade.OauthFacade;
import play.libs.F;
import play.mvc.Result;
import play.mvc.Security;
import py.gov.dncp.ws.framework.FacadeResponse;
import py.gov.dncp.ws.framework.ServiceController;

/**
 * Created by gaby.lorely on 08/03/2015.
 */
public class OauthController extends ServiceController {

    @Security.Authenticated(BasicAuthenticator.class)
    public static F.Promise<Result> generateAccessToken(){

        OauthFacade facade = getInstance(OauthFacade.class);
        F.Promise<FacadeResponse> resultadoPromise = facade.getAccessToken();
        return resultadoPromise.map(
                new F.Function<FacadeResponse, Result>() {
                    public Result apply(FacadeResponse response)
                            throws Throwable {
                        return response.getData() == null ? notFound()
                                : ok(response.getData().toString());
                    }
                }).recover(recuperarError());


    }

    /**
     * Si ocurre alguna excepción en el servicio base de la DNCP, se enmascara
     * mediante un error 500
     */
    private static F.Function<Throwable, Result> recuperarError() {
        return new F.Function<Throwable, Result>() {
            @Override
            public Result apply(Throwable t) throws Throwable {
                t.printStackTrace();
                return internalServerError("Error al obtener los datos, por favor intente de nuevo.");
            }
        };
    }
}
