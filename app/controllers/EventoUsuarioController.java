package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import autenticacion.BasicAuthenticator;

import facade.EventoUsuarioFacade;
import facade.SuscripcionFacade;
import models.EventoUsuario;
import models.TipoEvento;
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
            String mail = flash("mail");

            System.out.println("----LLAMADA A REGISTRAR EVENTOUSUARIO PARA: " + mail);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode json = request().body().asJson();
            System.out.println("JSON: " + String.valueOf(json));
            //List<TipoEvento> tipoEventos = mapper.readValue(json.asText(), new TypeReference<List<TipoEvento>>() { });
            List<TipoEvento> objects = new ObjectMapper().readValue(String.valueOf(json), new TypeReference<List<TipoEvento>>(){});
            for(TipoEvento e: objects){
                System.out.println("TIIPO EVENTO: " + e.getId());
            }
            EventoUsuarioFacade facade = getInstance(EventoUsuarioFacade.class);

            facade.registrar(mail, objects);

        }catch(Exception e){
            e.printStackTrace();
            return internalServerError("Error al registrar los datos, por favor intente de nuevo.");
        }

        return ok();
    }
}
