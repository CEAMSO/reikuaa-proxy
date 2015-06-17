package controllers;


import javax.ws.rs.PathParam;

import autenticacion.BasicAuthenticator;
import facade.DispositivoFacade;
import facade.OauthFacade;
import facade.SuscripcionFacade;
import models.Dispositivo;
import models.Evento;
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
    public static Result registrarSuscripcion(@PathParam("planificacion_id") String planificacion_id){
        System.out.println("----LLAMADA A REGISTRAR SUSCRIPCION PARA: " + flash(Usuario.Field.IDENTIFICADOR) + " planificacion_id: " + planificacion_id);
        String mail = flash(Usuario.Field.IDENTIFICADOR);

        try {
            SuscripcionFacade facade = getInstance(SuscripcionFacade.class);
            facade.registrar(mail, planificacion_id);
        }catch(Exception e){
            e.printStackTrace();
            return internalServerError("Error al registrar los datos, por favor intente de nuevo.");
        }

        return ok();


    }

    @Security.Authenticated(BasicAuthenticator.class)
    public static Result eliminarSuscripcion( @PathParam("planificacion_id") String planificacion_id){

        String mail = flash(Usuario.Field.IDENTIFICADOR);

        System.out.println("----LLAMADA A ELIMINAR SUSCRIPCION PARA: " + mail + " convocatoria: " + planificacion_id);

        try {
            SuscripcionFacade facade = getInstance(SuscripcionFacade.class);
            facade.eliminar(mail, planificacion_id);
        }catch(Exception e){
            play.Logger.error(e.getMessage());
            return internalServerError("Error al eliminar suscripcion, por favor intente de nuevo.");
        }

        return ok();


    }

    @Security.Authenticated(BasicAuthenticator.class)
    public static Result getSuscripciones(){

        String mail = flash("mail");
        play.Logger.info("----LLAMADA A GET  SUSCRIPCIONES PARA: " + mail );

        return ok();


    }

}
