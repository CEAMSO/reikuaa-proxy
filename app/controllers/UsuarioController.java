package controllers;

import autenticacion.BasicAuthenticator;
import autenticacion.BasicDispositivoAutenticator;
import facade.DispositivoFacade;
import facade.UsuarioFacade;
import models.Dispositivo;
import models.Usuario;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import py.gov.dncp.ws.framework.ServiceController;

import static play.mvc.Controller.flash;

/**
 * Created by gaby.lorely on 28/03/2015.
 */
public class UsuarioController extends ServiceController {

    /**
     * Llamado cuando se hace un post para registrar un nuevo dispositivo
     * Registra un telefono con sus datos de codigo y mail
     * @return
     */
    @Security.Authenticated(BasicDispositivoAutenticator.class)
    public static Result registrar() {
        System.out.println("-------------REGISTRAR USUARIO---------");
        try {
            Dispositivo reg = Form.form(Dispositivo.class).bindFromRequest().get();
            UsuarioFacade facade = getInstance(UsuarioFacade.class);
            facade.registrar(reg);

        }catch(Exception e){
           play.Logger.error(e.getMessage());
            return internalServerError("Error al registrar los datos, por favor intente de nuevo.");
        }

        return ok();
    }

    /**
     * Llamado cuando se hace un delete
     * @return
     */
    @Security.Authenticated(BasicDispositivoAutenticator.class)
    public static Result eliminar() {
        play.Logger.info("-------------ELIMINAR USUARIO---------");
        try {
            Dispositivo dispositivo = Form.form(Dispositivo.class).bindFromRequest().get();
            UsuarioFacade facade = getInstance(UsuarioFacade.class);
            facade.eliminarDispositivo(dispositivo);
        }catch(Exception e){
            return internalServerError("Error al eliminar los datos, por favor intente de nuevo.");
        }

        return ok();
    }


    /**
     * Llamado cuando se hace un delete
     * @return
     */
    @Security.Authenticated(BasicAuthenticator.class)
    public static Result actualizar() {
       play.Logger.info("-------------ACTUALIZAR DATOS DEL USUARIO---------");
        try {
            Dispositivo dispositivo = Form.form(Dispositivo.class).bindFromRequest().get();
            String registrationId = flash(Dispositivo.Field.REGISTRATION_ID);
            UsuarioFacade facade = getInstance(UsuarioFacade.class);
            facade.actualizarRegistrationId(dispositivo, registrationId);
        }catch(Exception e){
            play.Logger.error(e.getMessage());
            return internalServerError("Error al actualizar los datos, por favor intente de nuevo.");
        }

        return ok();
    }
}
