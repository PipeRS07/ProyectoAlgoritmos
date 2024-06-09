package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class eliminarCoursesController {

    @javafx.fxml.FXML
    private TextField idCursoEliminarField;
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
    public void buscarEliminarCursoOnAction(ActionEvent actionEvent) {

    }
    @javafx.fxml.FXML
    public void eliminarCurso(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void atrasDeEliminarOnAction(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }


}
