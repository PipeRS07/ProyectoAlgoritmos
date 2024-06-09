package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class MostrarCursosController {
    @javafx.fxml.FXML
    private TableView tableView;
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
    public void atrasDeMostrarCursosOnAction(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }
}
