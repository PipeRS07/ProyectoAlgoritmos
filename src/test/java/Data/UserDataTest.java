package Data;

import domain.clasesBase.Administrador;
import domain.clasesBase.Instructor;
import org.junit.jupiter.api.Test;
import util.Ruta;
import util.Utility;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
class UserDataTest {
    @Test
    void test1(){
        UserData data;
            try {
                data = new UserData();
                data.agregarAlFinal( new Administrador(2, "Felipe",  "jfelipe070703@gmail.com", Ruta.USUADMIN, util.Encriptacion.obtenerContraseniaCifrada("123")));
                data.agregarAlFinal( new Administrador(3, "Felipe3",  "jfelipe070703@gmail.com", Ruta.USUADMIN, util.Encriptacion.obtenerContraseniaCifrada("123")));
                System.out.println(Utility.usuariosRegistrados);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

    }



}