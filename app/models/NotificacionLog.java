package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Representa el log de notificaciones enviadas
 * Created by gaby.lorely on 14/03/2015.
 */
@Entity
@Table(name = "notificacion_log", schema = "notificacion")
public class NotificacionLog extends Model {

    @Id
    @Column(columnDefinition = "bigserial not null")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="notificacion.notificacion_log_id_seq")
    private Long id;

    @Column(name= "fecha_registro_id", columnDefinition = "timestamp without time zone not null")
    private Date fechaRegistro;

    @ManyToOne(optional = false)
    @Column(columnDefinition = "bigint not null")
    public Evento evento;

    String error;
    String messageId;
    String registrationId;

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }


}
