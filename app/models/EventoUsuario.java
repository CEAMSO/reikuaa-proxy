package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "evento_usuario", schema = "notificacion")
public class EventoUsuario extends Model {

    public static class Field {
        public static final String ID = "id";
        public static final String USUARIO = "usuario";
        public static final String GRUPO_EVENTO = "grupoEvento";
    }

    @Id
    @Column(columnDefinition = "bigserial not null")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="notificacion.evento_usuario_id_seq")
    public Long id;

    @ManyToOne(optional = false)
    @Column(columnDefinition = "bigint not null")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @Column(columnDefinition = "bigint not null")
    private GrupoEvento grupoEvento;

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

    public GrupoEvento getGrupoEvento() {
        return grupoEvento;
    }

    public void setGrupoEvento(GrupoEvento grupoEvento) {
        this.grupoEvento = grupoEvento;
    }
    public static Finder<Long, EventoUsuario> find = new Finder<Long, EventoUsuario>(
            Long.class, EventoUsuario.class
    );

}
