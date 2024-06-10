package controller;

import domain.bTree.TreeException;
import domain.clasesBase.Curso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Utility;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @FXML
    private ComboBox<String> levelComboBox;

    @FXML
    public void initialize() {
        levelComboBox.getItems().addAll("low", "medium", "high");
    }

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleRegisterCourse(ActionEvent actionEvent) {

        String courseId = courseNameField21.getText();
        String courseName = courseNameField2.getText();
        String description = descriptionField.getText();
        String durationText = courseNameField3.getText();
        String level = levelComboBox.getValue();
        String instructorId = courseNameField.getText();

        // Validar que los campos no estén vacíos
        if (courseId.isEmpty() || courseName.isEmpty() || description.isEmpty() || durationText.isEmpty() || level == null || instructorId.isEmpty()) {
            showAlert("Error", "Por favor complete todos los campos.", Alert.AlertType.ERROR);
            return;
        }

        try {

            int duration = Integer.parseInt(durationText);
            if (duration < 0) {
                throw new NumberFormatException();
            }

            // Crear un nuevo curso
            Curso newCourse = new Curso(courseName, description, durationText, level, courseId);

            // Verificar si el curso ya está registrado en el AVL
            if (Utility.cursosRegistrados.contains(newCourse)) {
                showAlert("Error", "El curso con este ID ya está registrado.", Alert.AlertType.ERROR);
            } else {
                // Agregar el nuevo curso al AVL
                Utility.cursosRegistrados.add(newCourse);
                showAlert("Éxito", "Curso registrado exitosamente.", Alert.AlertType.INFORMATION);
                clearFields();  // Limpiar los campos del formulario después del registro exitoso
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Formato de duración inválido. Use un número entero positivo.", Alert.AlertType.ERROR);
        } catch (domain.clasesBase.TreeException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        courseNameField21.clear();
        courseNameField2.clear();
        descriptionField.clear();
        courseNameField3.clear();
        courseNameField.clear();
        levelComboBox.setValue(null);
    }

    @FXML
    public void atrasDeCrearCurso(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
