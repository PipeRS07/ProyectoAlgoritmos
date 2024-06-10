package domain.clasesBase;

public class Estudiante extends User{
    private Curso cursoMatricula;
    public Estudiante(int id, String name, String email, String role, String contrasenia) {
        super(id, name, email, role, contrasenia);
    }
}
