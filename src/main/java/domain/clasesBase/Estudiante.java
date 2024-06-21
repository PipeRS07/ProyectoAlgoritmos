package domain.clasesBase;

import domain.list.SinglyLinkedList;

public class Estudiante extends User{

    private String retroalimentacion;
    private float tiempoEstudiado;




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


}
