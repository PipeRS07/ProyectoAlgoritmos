package controller;

import domain.clasesBase.Curso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import util.Utility;

import java.io.IOException;

public class editCourseController {

    @FXML
    private BorderPane bp;

    @FXML
    private TextField nameCursoField;

    @FXML
    private TextField descripcionField;

    @FXML
    private TextField duracionPerfilField;

    @FXML
    private ComboBox<?> levelEditarCursoOnAction;

    @FXML
    void actualizarEditarCursoOnAction(ActionEvent event) {

    }

    @FXML
    void atrasEditarCurso(ActionEvent event) {
        loadPage("managementCourse.fxml");
    }

    @FXML
    void buscarEditarOnAction(ActionEvent event) {

    }

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}