package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class registraCourseController {
    @javafx.fxml.FXML
    private TextField idUser;
    @javafx.fxml.FXML
    private DatePicker fecha;
    @javafx.fxml.FXML
    private TextField idCurso;
    @javafx.fxml.FXML
    private TextField id;
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
    public void RegisterCourse(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void atrasDeRegistroCurso(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }
}
