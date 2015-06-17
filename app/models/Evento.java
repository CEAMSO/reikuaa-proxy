package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import play.db.ebean.Model;

/**
 * Representa la apareción de un evento que debe ser notificado
 * Created by gaby.lorely on 14/03/2015.
 */
@Entity
@Table(name = "evento", schema = "notificacion")
public class Evento extends Model {

    public static class Field {
        public static final String ID = "id";
        public static final String NOTIFICADO_ANDROID = "notificadoAndroid";
        public static final String NOTIFICADO_IOS = "notificacionIos";

    }

    @Id
    @Column(columnDefinition = "bigserial not null")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notificacion.evento_id_seq")
    public Long id;

    @Column(name= "licitacion_planificacion_id", columnDefinition = "character varying(255) not null")
    private String planificacionId;

    @ManyToOne(optional = false)
    @Column(columnDefinition = "bigint not null")
    private TipoEvento tipoEvento;

    private String descripcion;

    private Boolean notificadoAndroid=false;

    private Boolean notificacionIos=false;

    @Transient
    private Long tipoEventoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Long getTipoEventoId() {
        return tipoEventoId;
    }

    public void setTipoEventoId(Long tipoEventoId) {
        this.tipoEventoId = tipoEventoId;
    }


    public String getPlanificacionId() {
        return planificacionId;
    }

    public void setPlanificacionId(String planificacionId) {
        this.planificacionId = planificacionId;
    }

    public Boolean getNotificadoAndroid() {
        return notificadoAndroid;
    }

    public void setNotificadoAndroid(Boolean notificadoAndroid) {
        this.notificadoAndroid = notificadoAndroid;
    }

    public Boolean getNotificacionIos() {
        return notificacionIos;
    }

    public void setNotificacionIos(Boolean notificacionIos) {
        this.notificacionIos = notificacionIos;
    }


    public static Finder<Long, Evento> find = new Finder<Long, Evento>(
            Long.class, Evento.class
    );

}
