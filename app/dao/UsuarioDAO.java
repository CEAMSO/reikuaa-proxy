package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Dispositivo;
import models.EventoUsuario;
import models.GrupoEvento;
import models.Suscripcion;
import models.TipoEvento;
import models.Usuario;

/**
 * Acceso a datos para el modelo de Usuario
 * Created by alejandro on 28/03/15.
 */
public class UsuarioDAO {

    public void save(Usuario usuario){
        usuario.save();
    }

    public void update(Usuario usuario){
        usuario.update();
    }
    /**
     * Retorna la lista de usuario suscriptos a un grupo de Evento
     * @param grupoEvento
     * @return
     */
    public List<Usuario> findSuscriptos(GrupoEvento grupoEvento, String planificacionId, String tipoDispositivo){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Map<String, Object> map = new HashMap<String,Object>();
        //eventosUsuario.TipoEvento.id =
        map.put(Usuario.Field.EVENTO_USUARIOS + "." + EventoUsuario.Field.GRUPO_EVENTO + "." + GrupoEvento.Field.ID, grupoEvento.getId());
        map.put(Usuario.Field.SUSCRIPCIONES + "." + Suscripcion.Field.PLANIFICACION_ID , planificacionId.trim());
        map.put(Usuario.Field.DISPOSITIVOS + "." + Dispositivo.Field.TIPO_DISPOSITIVO, tipoDispositivo);

        System.out.println("Grupo Evento: " + grupoEvento.getId());
        System.out.println("PlanificacionId: " + planificacionId);

        usuarios = Usuario.find.where().allEq(map).findList();
        System.out.println("Usuarios encontrados: " + usuarios.size());
        return usuarios;
    }

    public void delete(Usuario usuario){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Usuario.Field.IDENTIFICADOR, usuario.getIdentificador());

        Usuario user = Usuario.find.where().allEq(map).findUnique();
        user.delete();

    }

    public Usuario getByIdentificador(String identificador){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Usuario.Field.IDENTIFICADOR, identificador.trim());
        Usuario u = Usuario.find.where().allEq(map).findUnique();

        return u;
    }

    public Boolean tieneDispositivo(Usuario user, Dispositivo dispositivo){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(Usuario.Field.IDENTIFICADOR,user.getIdentificador());
        map.put(Usuario.Field.DISPOSITIVOS + "." + Dispositivo.Field.REGISTRATION_ID,dispositivo.getRegistrationId());
        map.put(Usuario.Field.DISPOSITIVOS + "." + Dispositivo.Field.TIPO_DISPOSITIVO, dispositivo.getTipoDispositivo());
        Usuario u = Usuario.find.where().allEq(map).findUnique();

        if(u != null){
            return true;
        }
        return false;
    }

    public List<Usuario> getByTipoDispositivo(String tipo) {

        List<Usuario> usuarios = new ArrayList<Usuario>();
        Map<String, Object> map = new HashMap<String,Object>();
        //eventosUsuario.TipoEvento.id =
        map.put(Usuario.Field.DISPOSITIVOS + "." + Dispositivo.Field.TIPO_DISPOSITIVO , tipo);

        usuarios = Usuario.find.where().allEq(map).findList();
        System.out.println("Usuarios encontrados: " + usuarios.size());
        return usuarios;
    }
}
