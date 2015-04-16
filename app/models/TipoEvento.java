package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Representa los diferentes tipos de eventos que se puede notificar
 * Created by gaby.lorely on 14/03/2015.
 */
@Entity
@Table(name = "tipo_eventos", schema="notificacion")
public class TipoEvento extends Model{

    @Id
    public Integer id;

    public String descripcion;

    public String template;

    //@ManyToMany(mappedBy = "tipoEvento")
    //private List<EventoUsuario> eventoUsuarios;

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getTemplate() {
        return template;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public static Finder<Integer, TipoEvento> find = new Finder<Integer, TipoEvento>(
            Integer.class, TipoEvento.class
    );


}
