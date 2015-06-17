package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import autenticacion.BasicAuthenticator;

import facade.EventoUsuarioFacade;
import facade.SuscripcionFacade;
import models.EventoUsuario;
import models.GrupoEvento;
import models.TipoEvento;
import models.Usuario;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Security;
import py.gov.dncp.ws.framework.ServiceController;

/**
 * Created by alejandro on 28/03/15.
 */
public class EventoUsuarioController extends ServiceController {

    /**
     * Llamado cuando se hace un post para actualizar los tipos de eventos a los cuales está
     * suscripto un Usuario
     * Actualiza una lista de EventoUsuario
     * @return
     */

    @BodyParser.Of(BodyParser.Json.class)
    @Security.Authenticated(BasicAuthenticator.class)
    public static Result actualizarEventosUsuario() {
        try {
            String id = flash(Usuario.Field.IDENTIFICADOR);

            System.out.println("----LLAMADA A REGISTRAR EVENTOUSUARIO PARA: " + id);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode json = request().body().asJson();
            Logger.info("JSON: " + String.valueOf(json));
            List<GrupoEvento> objects = new ObjectMapper().readValue(String.valueOf(json), new TypeReference<List<GrupoEvento>>(){});

            for(GrupoEvento e: objects){
                Logger.info("TIIPO EVENTO: " + e.getId());
            }

            EventoUsuarioFacade facade = getInstance(EventoUsuarioFacade.class);

            facade.registrar(id, objects);

        }catch(Exception e){
            Logger.error(e.getMessage());
            return internalServerError("Error al registrar los datos, por favor intente de nuevo.");
        }

        return ok();
    }
}
