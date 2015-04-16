package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Representa los dispositivos que tienen instalada la app
 * Created by gaby.lorely on 01/03/2015.
 */
@Entity
@Table(name = "usuarios", schema="notificacion")
public class Usuario extends Model {

    public static class Field {
        public static final String ID = "id";
        public static final String DISPOSITIVOS = "dispositivos";
        public static final String MAIL = "mail";
    }

    @Id
    public Integer id;

    public String mail;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dispositivo> dispositivos;

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Finder<Integer, Usuario> find = new Finder<Integer, Usuario>(
            Integer.class, Usuario.class
    );

    public Usuario(){
        super();
        this.dispositivos = new ArrayList<Dispositivo>();
    }
}
