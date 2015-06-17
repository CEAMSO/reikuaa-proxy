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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Representa los diferentes tipos de eventos que se puede notificar
 * Created by gaby.lorely on 14/03/2015.
 */
@Entity
@Table(name = "tipo_evento", schema = "notificacion")
public class TipoEvento extends Model{

    public static Integer CAMBIO_ESTADO = 1;
    public static Integer ADENDAS = 2;
    public static Integer SUBASTAS = 3;
    public static Integer PRORROGAS = 4;
    public static Integer ACLARACIONES = 5;
    public static Integer AVISOS = 6;
    public static Integer NUEVA_CONVOCANTE =    7;

    public static class Field {
        public static final String ID = "id";
        public static final String DESCRIPCION = "descripcion";
        public static final String NOMBRE = "nombre";
        public static final String GRUPO = "grupo";

    }

    @Id
    @Column(columnDefinition = "bigserial not null")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notificacion.tipo_evento_id_seq")
    private Long id;

    private String descripcion;

    private String nombre;

    @ManyToOne()
    @Column(columnDefinition = "bigint")
    public GrupoEvento grupoEvento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static Finder<Long, TipoEvento> find = new Finder<Long, TipoEvento>(
            Long.class, TipoEvento.class
    );

    public GrupoEvento getGrupo() {
        return grupoEvento;
    }

    public void setGrupo(GrupoEvento grupo) {
        this.grupoEvento = grupo;
    }


}
