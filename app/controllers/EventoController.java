package controllers;

import facade.EventoFacade;
import facade.UsuarioFacade;
import models.Dispositivo;
import models.Evento;
import play.Logger;
import play.data.Form;
import play.mvc.Result;
import py.gov.dncp.ws.framework.ServiceController;

/**
 * Manejador de creación de nuevos eventos
 * Created by gaby.lorely on 19/04/2015.
 */
public class EventoController extends ServiceController {

    public static Result registrarEvento() {
       Logger.info("-------------REGISTRAR EVENTO---------");
        try {
            Evento reg = Form.form(Evento.class).bindFromRequest().get();
            EventoFacade facade;
            facade = getInstance(EventoFacade.class);
            facade.registrar(reg);
        }catch(IllegalStateException ep){
           Logger.error(ep.getMessage());
            return badRequest();
        }catch (Exception e) {
            Logger.error(e.getMessage());
            return internalServerError("Error al registrar los datos, por favor intente de nuevo.");
        }

        return ok();
    }
}
