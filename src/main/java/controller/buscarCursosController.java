package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class buscarCursosController
{
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
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void buscarBuscarCursosOnAction(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void atrasBuscarCursosOnAcction(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }
}