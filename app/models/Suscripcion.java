package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

/**
 * Representa la suscripcion de un dispositivo a una convocatoria
 * Created by gaby.lorely on 14/03/2015.
 */
@Entity
@Table(name = "suscripcion", schema = "notificacion")
public class Suscripcion extends Model{

    public static class Field {
        public static final String ID = "id";
        public static final String USUARIO = "usuario";
        public static final String PLANIFICACION_ID = "planificacionId";
    }

    @Id
    @Column(columnDefinition = "bigserial not null")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notificacion.suscripcion_id_seq")
    private Long id;

    @ManyToOne(optional = false)
    @Column(columnDefinition = "bigint not null")
    private Usuario usuario;

    @Column(name= "licitacion_planificacion_id", columnDefinition = "character varying(255) not null")
    private String planificacionId;

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

    public static Finder<Long, Suscripcion> find = new Finder<Long, Suscripcion>(
            Long.class, Suscripcion.class
    );
    public String getPlanificacionId() {
        return planificacionId;
    }

    public void setPlanificacionId(String planificacionId) {
        this.planificacionId = planificacionId;
    }



}
