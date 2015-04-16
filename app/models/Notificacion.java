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
 * Notificaciones a dispositivos
 * Created by gaby.lorely on 14/03/2015.
 */
@Entity
@Table(name = "notificaciones", schema="notificacion")
public class Notificacion {

    @Id
    public Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dispositivo_id", referencedColumnName = "id")
    private Dispositivo dispositvo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;

    private Boolean notificado;

    public Boolean getNotificado() {
        return notificado;
    }

    public Evento getEvento() {
        return evento;
    }

    public Dispositivo getDispositvo() {
        return dispositvo;
    }

    public void setNotificado(Boolean notificado) {
        this.notificado = notificado;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setDispositvo(Dispositivo dispositvo) {
        this.dispositvo = dispositvo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
