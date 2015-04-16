package controllers;

import javax.ws.rs.PathParam;

import autenticacion.BasicAuthenticator;
import facade.DispositivoFacade;
import facade.OauthFacade;
import facade.SuscripcionFacade;
import models.Dispositivo;
import models.Usuario;
import play.data.Form;
import play.libs.F;
import play.mvc.Result;
import play.mvc.Security;
import py.gov.dncp.ws.framework.FacadeResponse;
import py.gov.dncp.ws.framework.ServiceController;

/**
 * Controllador para suscripcion
 * Created by gaby.lorely on 22/03/2015.
 */
public class SuscripcionController extends ServiceController {

    @Security.Authenticated(BasicAuthenticator.class)
    public static Result registrarSuscripcion( @PathParam("planificacion_id") String planificacionId){
        System.out.println("----LLAMADA A REGISTRAR SUSCRIPCION PARA: " + flash("mail") + " planificacionId: " + planificacionId);
        String mail = flash("mail");

        try {
            SuscripcionFacade facade = getInstance(SuscripcionFacade.class);
            facade.registrar(mail, planificacionId);
        }catch(Exception e){
            e.printStackTrace();
            return internalServerError("Error al registrar los datos, por favor intente de nuevo.");
        }

        return ok();


    }

    @Security.Authenticated(BasicAuthenticator.class)
    public static Result eliminarSuscripcion( @PathParam("planificacion_id") String planificacionId){

        String mail = flash("mail");
        System.out.println("----LLAMADA A ELIMINAR SUSCRIPCION PARA: " + mail + " convocatoria: " + planificacionId);

        try {
            SuscripcionFacade facade = getInstance(SuscripcionFacade.class);
            facade.eliminar(mail, planificacionId);
        }catch(Exception e){
            e.printStackTrace();
            return internalServerError("Error al eliminar suscripcion, por favor intente de nuevo.");
        }

        return ok();


    }

    @Security.Authenticated(BasicAuthenticator.class)
    public static Result getSuscripciones(){

        String mail = flash("mail");
        System.out.println("----LLAMADA A GET  SUSCRIPCIONES PARA: " + mail );

        return ok();


    }

}
