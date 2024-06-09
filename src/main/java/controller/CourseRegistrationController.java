package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class CourseRegistrationController {
    @javafx.fxml.FXML
    private TextField courseNameField;
    @javafx.fxml.FXML
    private TextField courseNameField2;
    @javafx.fxml.FXML
    private TextField courseNameField3;
    @javafx.fxml.FXML
    private TextArea descriptionField;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField courseNameField21;


    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @javafx.fxml.FXML
    public void handleRegisterCourse(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void atrasDeRegistroCursoOnAction(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }
}
