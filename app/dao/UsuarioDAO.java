package dao;

import java.util.HashMap;
import java.util.Map;

import models.Dispositivo;
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

    public void delete(Usuario usuario){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Usuario.Field.MAIL, usuario.getMail());

        Usuario user = Usuario.find.where().allEq(map).findUnique();
        user.delete();

    }

    public Usuario getByMail(String mail){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Usuario.Field.MAIL, mail.trim());
        Usuario u = Usuario.find.where().allEq(map).findUnique();

        return u;
    }

    public Boolean tieneDispositivo(Usuario user, Dispositivo dispositivo){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(Usuario.Field.MAIL,user.getMail());
        map.put(Usuario.Field.DISPOSITIVOS + "." + Dispositivo.Field.REGISTRATION_ID,dispositivo.getRegistrationId());
        map.put(Usuario.Field.DISPOSITIVOS + "." + Dispositivo.Field.TIPO_DISPOSITIVO, dispositivo.getTipoDispositivo());
        Usuario u = Usuario.find.where().allEq(map).findUnique();

        if(u != null){
            return true;
        }
        return false;
    }
}
