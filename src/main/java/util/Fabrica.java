package util;

import domain.clasesBase.Administrador;
import domain.clasesBase.Estudiante;
import domain.clasesBase.Instructor;
import domain.clasesBase.User;

public class Fabrica {

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
}
