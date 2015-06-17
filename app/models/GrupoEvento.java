package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Agrupador de tipos de eventos. Cada entrada se corresponde con una opción de configuración
 * en los dispositivos.|
 * Created by gaby.lorely on 07/06/2015.
 */
@Entity
@Table(name = "grupo_evento", schema = "notificacion")
public class GrupoEvento extends Model {

    public static Integer CAMBIO_ESTADO = 1;
    public static Integer ADENDAS = 2;

    public static class Field {
        public static final String ID = "id";
        public static final String DESCRIPCION = "descripcion";

    }

    @Id
    @Column(columnDefinition = "bigserial not null")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="notificacion.grupo_evento_id_seq")
    private Long id;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "grupoEvento",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TipoEvento> tiposEventos;


    @OneToMany(mappedBy = "grupoEvento",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventoUsuario> eventoUsuarios;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TipoEvento> getTiposEventos() {
        return tiposEventos;
    }

    public void setTiposEventos(List<TipoEvento> tiposEventos) {
        this.tiposEventos = tiposEventos;
    }

    public List<EventoUsuario> getEventoUsuarios() {
        return eventoUsuarios;
    }

    public void setEventoUsuarios(List<EventoUsuario> eventoUsuarios) {
        this.eventoUsuarios = eventoUsuarios;
    }

    public static Finder<Long, GrupoEvento> find = new Finder<Long, GrupoEvento>(
            Long.class, GrupoEvento.class
    );

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
