package controllers;

import models.Dispositivo;
import play.data.Form;
import play.mvc.*;
import py.gov.dncp.ws.framework.ServiceController;

import views.html.*;

public class Application extends ServiceController {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }


}
