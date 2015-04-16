package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Representa la suscripción de un dispositivo a un tipo de evento
 * Created by gaby.lorely on 14/03/2015.
 */
@Entity
@Table(name = "evento_usuario", schema="notificacion")
public class EventoUsuario extends Model {

    public static class Field {
        public static final String ID = "id";
        public static final String USUARIO = "usuario";
        public static final String USUARIO_ID = "usuario.id";
        public static final String TIPOEVENTO = "tipoEvento";
    }

    @Id
    public Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_evento_id", referencedColumnName = "id")
    private TipoEvento tipoEvento;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Finder<Integer, EventoUsuario> find = new Finder<Integer, EventoUsuario>(
            Integer.class, EventoUsuario.class
    );

}
