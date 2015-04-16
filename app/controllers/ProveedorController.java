package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.PathParam;

import autenticacion.BasicAuthenticator;
import facade.SuscripcionFacade;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Security;
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
}
