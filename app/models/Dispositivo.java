package models;


import java.util.List;

import javax.persistence.Column;
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
@Table(name = "dispositivo", schema = "notificacion")
public class Dispositivo extends Model {

    public static class Field {
        public static final String ID = "id";
        public static final String REGISTRATION_ID = "registrationId";
        public static final String TIPO_DISPOSITIVO = "tipoDispositivo";
        public static final String USUARIO = "usuario";
        public static final String IDENTIFICADOR = "identificador";
    }

    @Id
    @Column(columnDefinition = "bigserial not null")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notificacion.dispositivo_id_seq")
    public Long id;

    public String registrationId;

    public String tipoDispositivo;

    @ManyToOne(optional = false)
    @Column(columnDefinition = "bigint not null")
    private Usuario usuario;

    @Transient
    private String identificador;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Finder<Long, Dispositivo> find = new Finder<Long, Dispositivo>(
            Long.class, Dispositivo.class
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

}
