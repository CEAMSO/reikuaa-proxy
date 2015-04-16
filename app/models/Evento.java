package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa la apareción de un evento que debe ser notificado
 * Created by gaby.lorely on 14/03/2015.
 */
@Entity
@Table(name = "eventos", schema="notificacion")
public class Evento {


    @Id
    @SequenceGenerator(name = "EVENTO_GENERATOR", sequenceName = "evento_id_seq", schema = "notificacion",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENTO_GENERATOR")
    public Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "planificacion_id", referencedColumnName = "id")
    private String planificacionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_evento_id", referencedColumnName = "id")
    private TipoEvento tipoEvento;

    private String descripcion;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPlanificacionId() {
        return planificacionId;
    }

    public void setPlanificacionId(String planificacionId) {
        this.planificacionId = planificacionId;
    }
}
