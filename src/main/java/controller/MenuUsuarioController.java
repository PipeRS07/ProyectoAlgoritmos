package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class MenuUsuarioController {
    @javafx.fxml.FXML
    private BorderPane bp;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @javafx.fxml.FXML
    public void mostrarOnAction(ActionEvent actionEvent) {loadPage("mantenimientoUsuario.fxml");
    }

    @javafx.fxml.FXML
    public void registroOnAction(ActionEvent actionEvent) {loadPage("registrarUsuario.fxml");
    }

    @javafx.fxml.FXML
    public void inicioSesionOnAction(ActionEvent actionEvent) {loadPage("inicioSesionUsuario.fxml");
    }

    @javafx.fxml.FXML
    public void actualizaPerfilOnAction(ActionEvent actionEvent) {
        loadPage("actualizarUsuario.fxml");
    }
}
