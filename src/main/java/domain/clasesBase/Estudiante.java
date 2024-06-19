package domain.clasesBase;

import domain.list.SinglyLinkedList;

public class Estudiante extends User{
    private SinglyLinkedList inscripcionesCursos;
    private String retroalimentacion;
    private float tiempoEstudiado;

    public Estudiante(int id, String name, String email, String role, String contrasenia, SinglyLinkedList inscripciones) {
        super(id, name, email, role, contrasenia);
        this.inscripcionesCursos = new SinglyLinkedList();
    }


    public Estudiante(int id, String name, String email, String role, String contrasenia) {
        super(id, name, email, role, contrasenia);
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    public float getTiempoEstudiado() {
        return tiempoEstudiado;
    }

    public void setTiempoEstudiado(float tiempoEstudiado) {
        this.tiempoEstudiado = tiempoEstudiado;
    }

    public SinglyLinkedList getInscripcionesCursos() {
        return inscripcionesCursos;
    }

    public void setInscripcionesCursos(SinglyLinkedList inscripcionesCursos) {
        this.inscripcionesCursos = inscripcionesCursos;
    }
}
