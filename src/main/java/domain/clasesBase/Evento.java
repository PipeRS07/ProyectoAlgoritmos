package domain.clasesBase;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Evento {
    private LocalDate fechaYHora;
    private User User;
    private String descripcion;

    public Evento(User User, String descripcion) {
        this.User = User;
        this.descripcion = descripcion;
        this.fechaYHora = LocalDate.now();
    }

    public LocalDate getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDate fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
