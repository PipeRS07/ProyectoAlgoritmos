package domain.clasesBase;

import domain.list.SinglyLinkedList;

public class Instructor extends User{

    private SinglyLinkedList cursosRegistrados;

    public Instructor(int id, String name, String email, String role, String contrasenia) {
        super(id, name, email, role, contrasenia);
    }

    public Instructor(int id, String name, String email, String role, String contrasenia, SinglyLinkedList cursosRegistrados) {
        super(id, name, email, role, contrasenia);
        this.cursosRegistrados = cursosRegistrados;
    }

    public SinglyLinkedList getCursosRegistrados() {
        return cursosRegistrados;
    }

    public void setCursosRegistrados(SinglyLinkedList cursosRegistrados) {
        this.cursosRegistrados = cursosRegistrados;
    }
}
