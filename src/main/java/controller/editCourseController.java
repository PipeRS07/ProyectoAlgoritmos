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

public class EditCourseController {

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

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void actualizarEditarCursoOnAction(ActionEvent actionEvent) {
        boolean bandera = false;
        try {
            String nombre = nameCursoField.getText();
            String descripcion = descripcionField.getText();
            String duracion = duracionPerfilField.getText();
            String dificultad = "";


            for (int i = 0; i < Utility.cursosRegistrados.size(); i++) {
                Curso aux = (Curso) Utility.cursosRegistrados.get(i); // Asumiendo que el método get(i) está definido en tu estructura de datos
                if (aux.getId() == Integer.parseInt(buscar.getText())) {
                    // Remover el curso existente de la lista
                    Utility.cursosRegistrados.remove(aux);

                    //actualiza datos del curso
                    aux.setNombre(nombre);
                    aux.setDescripcion(descripcion);
                    aux.setDuracion(duracion);
                    aux.setDificultad(dificultad); // Aquí debes establecer la dificultad adecuadamente

                    //Agregar a la lista
                    Utility.cursosRegistrados.add(aux);
                    // en true ya que el curso ha sido actualizado
                    bandera = true;
                    showAlert("Éxito", "Curso actualizado correctamente", Alert.AlertType.INFORMATION);
                    break;
                }
            }

            if (!bandera) {
                showAlert("Error", "El curso no se encuentra registrado", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("Error", "Hubo un problema al actualizar el curso", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void atrasEditarCursoOnAction(ActionEvent actionEvent) {
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