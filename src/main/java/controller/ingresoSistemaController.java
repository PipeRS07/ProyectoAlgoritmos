package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class ingresoSistemaController {
    @javafx.fxml.FXML
    private TextField usernameField;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private Label messageLabel;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void registrarseOnAction(ActionEvent actionEvent) {
        loadPage("registrarUsuario.fxml");
    }

    @javafx.fxml.FXML
    public void inicioSecionnAction(ActionEvent actionEvent) {

        loadPage("hello-view.fxml");
    }
}
