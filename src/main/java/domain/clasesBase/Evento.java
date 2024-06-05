package domain.clasesBase;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Evento {
    private LocalDate fechaYHora;
    private Usuario usuario;
    private String descripcion;

    public Evento(Usuario usuario, String descripcion) {
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.fechaYHora = LocalDate.now();
    }

    public LocalDate getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDate fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
