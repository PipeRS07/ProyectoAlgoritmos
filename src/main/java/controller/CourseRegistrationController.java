package controller;

import domain.bTree.TreeException;
import domain.clasesBase.AVL;
import domain.clasesBase.Curso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Utility;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CourseRegistrationController {
    @javafx.fxml.FXML
    private TextField courseNameField;
    @javafx.fxml.FXML
    private TextArea descriptionField;
    @javafx.fxml.FXML
    private BorderPane bp;
    @FXML
    private ComboBox<String> levelComboBox;
    @FXML
    private DatePicker fechaInicioRegistroCurso;
    @FXML
    private DatePicker fechaFinalRegistroCurso;
    @FXML
    private TextField courseIdField;
    @FXML
    private TextField instructorNameRegistroCursoField;
    @FXML
    private Label durationLabel;
    private AVL cursos ;

    @FXML
    public void initialize() {
        this.cursos=Utility.cursosRegistrados;
        System.err.println(cursos+"initialize");
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
        int courseId = Integer.parseInt(courseIdField.getText());
        String courseName = courseNameField.getText();
        String description = descriptionField.getText();

        // Obtener las fechas de inicio y final
        LocalDate startDate = fechaInicioRegistroCurso.getValue();
        LocalDate endDate = fechaFinalRegistroCurso.getValue();

        if (startDate == null || endDate == null) {
            showAlert("Error", "Por favor seleccione las fechas de inicio y final.", Alert.AlertType.ERROR);
            return;
        }

        // Calcular la duración en días
        long duration = ChronoUnit.DAYS.between(startDate, endDate);
        if (duration < 0) {
            showAlert("Error", "La fecha de finalización no puede ser anterior a la fecha de inicio.", Alert.AlertType.ERROR);
            return;
        }

        String durationText = String.valueOf(duration);
        String level = levelComboBox.getValue();
        String instructorId = instructorNameRegistroCursoField.getText();

        // Validar que los campos no estén vacíos
        if (this.courseIdField.getText().isEmpty() || courseName.isEmpty() || description.isEmpty() || level == null || instructorId.isEmpty()) {
            showAlert("Error", "Por favor complete todos los campos.", Alert.AlertType.ERROR);
            return;
        }

        try {

            // Crear un nuevo curso
            //Curso newCourse = new Curso(courseName, description, durationText, level, courseId);
            Curso newCourse = new Curso(courseName, courseId, description, durationText, level, "");

            // Verificar si el curso ya está registrado en el AVL
            if (!Utility.cursosRegistrados.isEmpty()) {
                if (Utility.cursosRegistrados.contains(newCourse.getNombre())&&Utility.cursosRegistrados.contains(newCourse.getId())) {
                    showAlert("Error", "El curso con este ID ya está registrado.", Alert.AlertType.ERROR);
                } else {
                    // Agregar el nuevo curso al AVL
                    cursos.add(newCourse);

//                    System.out.println("Cursos registrados" + Utility.cursosRegistrados);
//                    System.err.println("root\n\n" + Utility.cursosRegistrados.root.data);
                    showAlert("Éxito", "Curso registrado exitosamente.", Alert.AlertType.INFORMATION);
                    clearFields();  // Limpiar los campos del formulario después del registro exitoso
                }
            } else {
//                // Agregar el nuevo curso al AVL
                Utility.cursosRegistrados.add(newCourse);
//                System.out.println("Cursos registrados" + Utility.cursosRegistrados);
//                System.err.println("root\n\n" + Utility.cursosRegistrados.root.data);
                showAlert("Éxito", "Curso registrado exitosamente.", Alert.AlertType.INFORMATION);
                clearFields();  // Limpiar los campos del formulario después del registro exitoso
            }
        } catch (domain.clasesBase.TreeException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        courseIdField.clear();
        instructorNameRegistroCursoField.clear();
        descriptionField.clear();
        fechaInicioRegistroCurso.setValue(null);
        fechaFinalRegistroCurso.setValue(null);
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