package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Representa los dispositivos que tienen instalada la app
 * Created by gaby.lorely on 01/03/2015.
 */
@Entity
@Table(name = "usuario", schema = "notificacion")
public class Usuario extends Model {

    public static class Field {
        public static final String ID = "id";
        public static final String DISPOSITIVOS = "dispositivos";
        public static final String IDENTIFICADOR = "identificador";
        public static final String EVENTO_USUARIOS = "eventoUsuarios";
        public static final String SUSCRIPCIONES = "suscripciones";
    }

    @Id
    @Column(columnDefinition = "bigserial not null")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="notificacion.usuario_id_seq")
    public Long id;

    @Column(columnDefinition = "character varying(255) not null")
    public String identificador;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dispositivo> dispositivos;

    @OneToMany(mappedBy = "usuario", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventoUsuario> eventoUsuarios;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Suscripcion> suscripciones;

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Finder<Long, Usuario> find = new Finder<Long, Usuario>(
            Long.class, Usuario.class
    );

    public List<EventoUsuario> getEventoUsuarios() {
        return eventoUsuarios;
    }

    public void setEventoUsuarios(List<EventoUsuario> eventoUsuarios) {
        this.eventoUsuarios = eventoUsuarios;
    }

    public Usuario(){
        super();
        this.dispositivos = new ArrayList<Dispositivo>();
    }
}
