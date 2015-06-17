package controllers;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import autenticacion.BasicAuthenticator;
import facade.CapacitacionFacade;
import facade.OauthFacade;
import play.Logger;
import play.libs.F;
import play.mvc.Result;
import play.mvc.Security;
import py.gov.dncp.ws.framework.FacadeResponse;
import py.gov.dncp.ws.framework.ServiceController;

/**
 * Created by gaby.lorely on 22/05/2015.
 */
public class CapacitacionController  extends ServiceController {

    @Security.Authenticated(BasicAuthenticator.class)
    public static F.Promise<Result> getCapacitaciones( @QueryParam("offset") String offset, @QueryParam("limit") String limit){

        CapacitacionFacade facade = getInstance(CapacitacionFacade.class);
        F.Promise<FacadeResponse> resultadoPromise = facade.getCapacitaciones(offset, limit);
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
                Logger.error("Error: " +  t.getMessage() );
                Logger.error("A causa de:" + t.getCause());
                return internalServerError("Error al obtener los datos, por favor intente de nuevo.");
            }
        };
    }
}
