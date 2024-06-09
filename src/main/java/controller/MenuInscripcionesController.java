package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class MenuInscripcionesController {
    @javafx.fxml.FXML
    private BorderPane bp;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Deprecated
    public void handleEnrollmentCancellation(ActionEvent actionEvent)
    {loadPage("cancelarInscripcion.fxml");

    }

    @Deprecated
    public void handleAdminEnrollmentManagement(ActionEvent actionEvent) {
        loadPage("gestionAdmin.fxml");
    }

    @Deprecated
    public void handleStudentEnrollment(ActionEvent actionEvent) {
        loadPage("inscripcionEstudiante.fxml");
    }

    @javafx.fxml.FXML
    public void registroOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void inicioSesionOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void actualizaPerfilOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void mostrarOnAction(ActionEvent actionEvent) {
    }
}
