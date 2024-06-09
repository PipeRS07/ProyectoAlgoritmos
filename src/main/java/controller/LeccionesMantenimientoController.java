package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class LeccionesMantenimientoController {
    @FXML
    private TextField correoPerfilField;
    @FXML
    private TextField busquedaField;
    @FXML
    private TextField nombrePerfilField;
    @FXML
    private TextField busquedaField1;
    @FXML
    private PasswordField contraseñaPerfilField;
    @FXML
    private TextField correoField;
    @FXML
    private TextField contraseñaField;
    @FXML
    private TableView tableView;
    @FXML
    private TextField nombreField;
    @FXML
    private BorderPane bp;

    @Deprecated
    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void actualizarPerfil(ActionEvent actionEvent) {


    }

    @FXML
    public void buscarUsuario(ActionEvent actionEvent) {
    }

    @FXML
    public void agregarUsuario(ActionEvent actionEvent) {
    }

    @FXML
    public void eliminarUsuario(ActionEvent actionEvent) {
    }

    @Deprecated
    private void handleSeguridad(ActionEvent event) {
        loadPage("Login.fxml");
    }

    @Deprecated
    private void handleUsuarios(ActionEvent event) {
        loadPage("mantenimientoUsuario.fxml");
    }

    @Deprecated
    private void handleCursos(ActionEvent event) { loadPage("managementCourse.fxml");
    }

    @Deprecated
    private void handleLecciones(ActionEvent event) {
        loadPage("mantenimientoLecciones.fxml");    }

    @Deprecated
    private void handleInscripciones(ActionEvent event) {
        loadPage("menuInscripciones.fxml");
    }

    @Deprecated
    private void handleMisCursos(ActionEvent event) {
    }

    @Deprecated
    private void handleProgreso(ActionEvent event) {
        showAlert("Mi Aprendizaje - Progreso", "Aquí puede ver su progreso.");
    }

    @Deprecated
    private void handleInformes(ActionEvent event) {
        showAlert("Reportes - Informes", "Aquí puede generar informes.");
    }

    @Deprecated
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
