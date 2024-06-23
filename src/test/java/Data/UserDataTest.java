package Data;

import domain.clasesBase.Administrador;
import domain.clasesBase.Instructor;
import domain.clasesBase.Reporte;
import domain.clasesBase.User;
import org.junit.jupiter.api.Test;
import util.Ruta;
import util.Utility;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
class UserDataTest {
    @Test
    void test1(){
        PdfData pdfData = new PdfData("informe");
        pdfData.generarYEnviarPDF(new Reporte("Hola", "content", "veronicaAgueroAguilar@gmail.com"));


                UserData data;
            try {
                data = new UserData();
              //  data.agregarAlFinal(new User(14, "A", "email", Ruta.USUINSTRUCTOR, util.Encriptacion.obtenerContraseniaCifrada("14")));
                data.agregarAlFinal(new User(16, "A", "email", Ruta.USUESTUDIANTE, util.Encriptacion.obtenerContraseniaCifrada("16")));

                System.out.println(Utility.usuariosRegistrados);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

    }



}