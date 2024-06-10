
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class HelloController {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text txtMessage;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void Home(ActionEvent event) {
        this.txtMessage.setText("Proyecto");
        this.bp.setCenter(ap);
    }



    @FXML
    private void handleUsuarios(ActionEvent event) {
        loadPage("menuUsuario.fxml");
    }

    @FXML
    private void handleCursos(ActionEvent event) { loadPage("managementCourse.fxml");
    }

    @FXML
    private void handleLecciones(ActionEvent event) {
        loadPage("mantenimientoLecciones.fxml");    }

    @FXML
    private void handleInscripciones(ActionEvent event) {
        loadPage("menuInscripciones.fxml");
    }

    @FXML
    private void handleMisCursos(ActionEvent event) {
        showAlert("Mis cursos", "Aquí puede ver sus curso.");

    }

    @FXML
    private void handleProgreso(ActionEvent event) {
        loadPage("aprendizajeLinea.fxml");
        showAlert("Mi Aprendizaje - Progreso", "Aquí puede ver su progreso.");
    }

    @FXML
    private void handleInformes(ActionEvent event) {
        loadPage("reporte.fxml");
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

    @Deprecated
    public void Registro(ActionEvent actionEvent) {
        loadPage("ingresoSistema.fxml");
    }

    @Deprecated
    public void registraseOnAction(ActionEvent actionEvent) {
        loadPage("registroCurso.fxml");

    }

    @FXML
    public void cerrarSesionOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void registrarCurso(ActionEvent actionEvent) {
    }

    @FXML
    public void inicioAdmiOnAction(ActionEvent actionEvent) {
        loadPage("inicioSesionAdmin.fxml");
    }
}