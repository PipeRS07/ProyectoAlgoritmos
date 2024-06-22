package controller;

import domain.clasesBase.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Ruta;

import java.io.IOException;

public class MenuInscripcionesController {
    @javafx.fxml.FXML
    private BorderPane bp;
    private User UserActivo;
    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @javafx.fxml.FXML
    public void handleEnrollmentCancellation(ActionEvent actionEvent) {
        if (UserActivo != null && (UserActivo.getRole().equals(Ruta.USUESTUDIANTE))) {
            loadPage("cancelarInscripcion.fxml");
        }


    }

    @javafx.fxml.FXML
    public void handleAdminEnrollmentManagement(ActionEvent actionEvent) {
        loadPage("gestionAdmin.fxml");
    }

    @javafx.fxml.FXML
    public void handleStudentEnrollment(ActionEvent actionEvent) {
        loadPage("inscripcionEstudiante.fxml");
    }


}
