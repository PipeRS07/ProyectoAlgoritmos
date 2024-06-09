package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class editCourseController {
    @javafx.fxml.FXML
    private TextField courseLengthField1;
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private TextField courseLengthField;
    @javafx.fxml.FXML
    private TextField descriptionField;
    @javafx.fxml.FXML
    private ComboBox comboBox;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField nameField1;

    @javafx.fxml.FXML
    public void handleCreateCourse(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void regresarOnAction(ActionEvent actionEvent) {
        loadPage("couseView.fxml");
    }
    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void atrasDeEditasrCursoOnAction(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }
}
