package models.notificaciones;

/**
 * Created by gaby.lorely on 19/04/2015.
 */
public class Data {

    private String titulo;
    private String detalle;
    private String idLlamado;

    public Long getGrupoEvento() {
        return grupoEvento;
    }

    public void setGrupoEvento(Long grupoEvento) {
        this.grupoEvento = grupoEvento;
    }

    private Long grupoEvento;

    public String getIdLlamado() {
        return idLlamado;
    }

    public void setIdLlamado(String idLlamado) {
        this.idLlamado = idLlamado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
