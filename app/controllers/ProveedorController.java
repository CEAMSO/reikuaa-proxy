package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.logging.Logger;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import autenticacion.BasicAuthenticator;
import facade.CapacitacionFacade;
import facade.ProveedorFacade;
import facade.SuscripcionFacade;
import play.libs.F;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Security;
import py.gov.dncp.ws.framework.FacadeResponse;
import py.gov.dncp.ws.framework.ServiceController;

/**
 * Manejador de eventos relacionados con proveedores
 * Created by gaby.lorely on 13/04/2015.
 */
public class ProveedorController  extends ServiceController {

    /**
     * Servicio que siempre retorna ok con el slug = ruc para validar
     * un ruc de proveedor
     * @param ruc
     * @return
     */
    @Security.Authenticated(BasicAuthenticator.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result getSlugProveedor( @PathParam("ruc") String ruc){
        ObjectNode result = Json.newObject();
        result.put("slug",ruc);
        return ok(result);

    }


    @Security.Authenticated(BasicAuthenticator.class)
    public static F.Promise<Result> getProveedor( @PathParam("ruc") String ruc){

        ProveedorFacade facade = getInstance(ProveedorFacade.class);
        F.Promise<FacadeResponse> resultadoPromise = facade.getProveedor(ruc);
        return resultadoPromise.map(
                new F.Function<FacadeResponse, Result>() {
                    public Result apply(FacadeResponse response)
                            throws Throwable {
                        return response.getData() == null ? notFound()
                                : ok(response.getData().toString());
                    }
                }).recover(recuperarError());
    }

    @Security.Authenticated(BasicAuthenticator.class)
    public static F.Promise<Result> getCategoriasPorProveedor( @PathParam("slug") String slug){

        ProveedorFacade facade = getInstance(ProveedorFacade.class);
        F.Promise<FacadeResponse> resultadoPromise = facade.getCategoriasPorProveedor(slug);
        return resultadoPromise.map(
                new F.Function<FacadeResponse, Result>() {
                    public Result apply(FacadeResponse response)
                            throws Throwable {
                        return response.getData() == null ? notFound()
                                : ok(response.getData().toString());
                    }
                }).recover(recuperarError());
    }

    @Security.Authenticated(BasicAuthenticator.class)
    public static F.Promise<Result> getProductosPorProveedor( @PathParam("slug") String slug){

        ProveedorFacade facade = getInstance(ProveedorFacade.class);
        F.Promise<FacadeResponse> resultadoPromise = facade.getProductosPorProveedor(slug);
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
                play.Logger.error(t.getMessage());
                play.Logger.error("Causa: " + t.getCause());
                return internalServerError("Error al obtener los datos, por favor intente de nuevo.");
            }
        };
    }
}
