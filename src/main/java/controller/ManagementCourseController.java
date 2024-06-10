package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class ManagementCourseController {

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
    @javafx.fxml.FXML
    public void searchOnAction(ActionEvent actionEvent) {
        loadPage("buscarCursos.fxml");
    }

    @javafx.fxml.FXML
    public void editOnAction(ActionEvent actionEvent) {
        loadPage("editarCurso.fxml");
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {loadPage("eliminarCurso.fxml");
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        loadPage("registroCurso.fxml");
    }

    @javafx.fxml.FXML
    public void showOnAction(ActionEvent actionEvent) {loadPage("mostrarCursos.fxml");
    }
}
