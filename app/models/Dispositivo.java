package models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import play.db.ebean.Model;

/**
 * Representa los dispositivos que tienen instalada la app
 * Created by gaby.lorely on 01/03/2015.
 */
@Entity
@Table(name = "dispositivos", schema="notificacion")
public class Dispositivo extends Model {

    public static class Field {
        public static final String ID = "id";
        public static final String REGISTRATION_ID = "registrationId";
        public static final String TIPO_DISPOSITIVO = "tipoDispositivo";
        public static final String USUARIO = "usuario";
        public static final String MAIL = "mail";
    }

    @Id
    public Integer id;

    public String registrationId;

    public String tipoDispositivo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Transient
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Finder<Integer, Dispositivo> find = new Finder<Integer, Dispositivo>(
            Integer.class, Dispositivo.class
    );

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }



}
