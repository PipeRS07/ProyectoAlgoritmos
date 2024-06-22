package domain.clasesBase;

public class Inscripcion {
    private Estudiante estudiante;
    private Curso curso;
    private String status; // Nuevo campo para mantener el estado

    public Inscripcion(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.status = "Pendiente"; // Estado por defecto
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getStatus() {
        return status;
    }

    public void approve() {
        this.status = "Aprobada";
    }

    public void setStatus(String aprobado) {
    }

}
