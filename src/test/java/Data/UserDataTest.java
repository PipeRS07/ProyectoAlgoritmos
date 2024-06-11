package Data;

import domain.clasesBase.Administrador;
import domain.clasesBase.Instructor;
import org.junit.jupiter.api.Test;
import util.Ruta;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
class UserDataTest {
    @Test
    void test1(){
        UserData data;
            try {
                data = new UserData();
                data.agregarAlFinal( new Administrador(1, "Admin",  "jfelipe070703@gmail.com", Ruta.USUADMIN, util.Encriptacion.obtenerContraseniaCifrada("123")));
                
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

    }



}