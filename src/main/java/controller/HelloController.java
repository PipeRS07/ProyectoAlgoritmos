package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class HelloController {
    @FXML
    private BorderPane bp;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void handleSeguridad(ActionEvent event) {
        loadPage("Login.fxml");
    }

    @FXML
    private void handleUsuarios(ActionEvent event) {
        loadPage("userView.fxml");
    }

    @FXML
    private void handleCursos(ActionEvent event) { loadPage("managementCourse.fxml");
  }

    @FXML
    private void handleLecciones(ActionEvent event) {
        showAlert("Mantenimiento de Lecciones", "Aquí puede gestionar las lecciones.");
    }

    @FXML
    private void handleInscripciones(ActionEvent event) {
        showAlert("Mantenimiento de Inscripciones", "Aquí puede gestionar las inscripciones.");
    }

    @FXML
    private void handleMisCursos(ActionEvent event) {
    }

    @FXML
    private void handleProgreso(ActionEvent event) {
        showAlert("Mi Aprendizaje - Progreso", "Aquí puede ver su progreso.");
    }

    @FXML
    private void handleInformes(ActionEvent event) {
        showAlert("Reportes - Informes", "Aquí puede generar informes.");
    }

    @FXML
    private void handleAnalisis(ActionEvent event) {
        showAlert("Reportes - Análisis", "Aquí puede realizar análisis.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}