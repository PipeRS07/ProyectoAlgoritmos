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
                data.agregarAlFinal( new Administrador(21, "Nigel",  "email", Ruta.USUADMIN, util.Encriptacion.obtenerContraseniaCifrada("1")));
                data.agregarAlFinal(new Instructor(31, "Veroa", "@gmailgg", Ruta.USUINSTRUCTOR, "hola123"));

                EnviarEmail enviarEmail = new EnviarEmail();
                enviarEmail.enviarCorreo("veronicaagueroaguilar@gmail.com", "", "", "");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

    }



}