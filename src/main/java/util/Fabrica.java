package util;

import domain.bTree.AVLTree;
import domain.clasesBase.*;
import domain.list.CircularDoublyLinkedList;


import java.time.LocalDate;

public class Fabrica {

    public static User fabricaUsuarios(int id, String name, String email, String role, String contrasenia){
        if(role.equals(Ruta.USUADMIN)){
            return new Administrador(id, name, email, role, contrasenia);
        }
        if(role.equals(Ruta.USUINSTRUCTOR)){
            return new Instructor(id, name, email, role, contrasenia);
        }
        if(role.equals(Ruta.USUESTUDIANTE)){
            return new Estudiante(id, name, email, role, contrasenia);
        }
        return null;
    }



    public static User fabricaUsuarios(User usuario){
        if(usuario.getRole().equals(Ruta.USUADMIN)){
            return new Administrador(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getRole(), usuario.getContrasenia());
        }
        if(usuario.getRole().equals(Ruta.USUINSTRUCTOR)){
            return new Instructor(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getRole(), usuario.getContrasenia());
        }
        if(usuario.getRole().equals(Ruta.USUESTUDIANTE)){
            return new Estudiante(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getRole(), usuario.getContrasenia());
        }
        return null;
    }

    public static Curso fabricaCursos(String nombre, String descripcion, LocalDate duracion, String dificultad, String siglas) {
        return new Curso(nombre, descripcion, duracion, dificultad, siglas);
    }

    public static void fabricaTDA(Object tda, Object elemento) {
        if (tda instanceof CircularDoublyLinkedList && elemento instanceof User) {
            ((CircularDoublyLinkedList) tda).add(elemento);
        } else if (tda instanceof AVLTree && elemento instanceof Curso) {
            ((AVLTree) tda).add(elemento);
        }
    }
}