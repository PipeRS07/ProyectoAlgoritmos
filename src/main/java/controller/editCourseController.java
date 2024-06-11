package controller;

import domain.clasesBase.Curso;
import domain.clasesBase.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Ruta;
import util.Utility;

import java.io.IOException;

public class editCourseController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField courseLengthField;
    @FXML
    private TextField descriptionField;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private BorderPane bp;
    @FXML
    private TextField courseLengthField1;
    @FXML
    private TextField id;
    @FXML
    private TextField nameField1;

    private Curso curso;

//    public void initialize() {
//        comboBox.getItems().addAll());
//    }


    @FXML
    public void handleCreateCourse(ActionEvent actionEvent) {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String durationStr = courseLengthField.getText();
        String level = comboBox.getValue();
        String id= this.id.getText();

        if (id.isEmpty()) {
            mostrarAlerta("Error", "Se debe de ingresar un Id para buscar");
            return;
        }

        try {
             curso= (Curso) Utility.cursosRegistrados.findNode(id).data;


        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La duración debe ser un número entero");
            return;
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

       // Curso newCurso = new Curso(name, description, Integer.toString(duration), level);

       // Utility.cursosRegistrados.add(newCurso);
        mostrarAlerta("Éxito", "El curso ha sido creado exitosamente");
        limpiarCampos();
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
    public void atrasDeEditarCursoOnAction(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }

    @FXML
    public void actualizaOnAction(ActionEvent actionEvent) {
        String name = nameField.getText();

        if (name.isEmpty()) {
            mostrarAlerta("Error", "El campo del nombre del curso está vacío");
            return;
        }

        if (curso == null) {
            mostrarAlerta("Error", "El curso no está registrado");
            return;
        }

        //int duration = Integer.parseInt(durationStr);
        curso.setNombre(name);
        curso.setDescripcion(descriptionField.getText());
        curso.setDuracion(String.valueOf(Integer.parseInt(courseLengthField.getText())));
        curso.setDificultad(comboBox.getValue());

        mostrarAlerta("Éxito", "El curso ha sido actualizado correctamente");
    }

    @FXML
    public void limpiarOnAction(ActionEvent actionEvent) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        nameField.clear();
        descriptionField.clear();
        courseLengthField.clear();
        comboBox.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
