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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
