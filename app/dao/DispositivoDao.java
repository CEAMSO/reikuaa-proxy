package dao;

import java.util.HashMap;
import java.util.Map;

import models.Dispositivo;
import models.Usuario;

/**
 * Created by gaby.lorely on 08/03/2015.
 */
public class DispositivoDao {

    /**
     * Actualiza el codigo de notificación si encuentra un dispositivo con el mismo mail registrado
     * o bien persiste una nueva entidad de dispositivo
     * @param dispositivo
     * @return
     */
    public Dispositivo save(Dispositivo dispositivo){
        dispositivo.save();
        return dispositivo;
    }

    public void delete(Dispositivo dispositivo){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Dispositivo.Field.REGISTRATION_ID, dispositivo.getRegistrationId());
        map.put(Dispositivo.Field.TIPO_DISPOSITIVO, dispositivo.getTipoDispositivo());
        map.put(Dispositivo.Field.USUARIO + "." + Usuario.Field.IDENTIFICADOR, dispositivo.getIdentificador());

        Dispositivo user = Dispositivo.find.where().allEq(map).findUnique();
        if(user != null) {
            user.delete();
        }

    }

    public Dispositivo update(Dispositivo dispositivo){
        dispositivo.update();
        return dispositivo;
    }

    /**
     * Encuentra el dispositivo correspondiente al registration id, mail y tipo dispositivo
     * @param identificador
     * @param registrationId
     * @param tipoDispositivo
     * @return
     */
    public Dispositivo find(String identificador, String registrationId, String tipoDispositivo) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Dispositivo.Field.REGISTRATION_ID, registrationId);
        map.put(Dispositivo.Field.USUARIO + "." + Usuario.Field.IDENTIFICADOR, identificador);
        map.put(Dispositivo.Field.TIPO_DISPOSITIVO, tipoDispositivo);
        Dispositivo d = Dispositivo.find.where().allEq(map).findUnique();
        d.setIdentificador(identificador);
        return d;
    }

    /**
     * Encuentra el dispositivo correspondiente al registration id y mail
     * @param mail
     * @param registrationId
     * @return
     */
    public Dispositivo find(String mail, String registrationId) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put(Dispositivo.Field.REGISTRATION_ID, registrationId);
        map.put(Dispositivo.Field.USUARIO + "." + Usuario.Field.IDENTIFICADOR, mail);
        Dispositivo d = Dispositivo.find.where().allEq(map).findUnique();
        d.setIdentificador(mail);
        return d;
    }


}
