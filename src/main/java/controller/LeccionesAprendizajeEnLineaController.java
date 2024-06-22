package controller;

import domain.clasesBase.Curso;
import domain.clasesBase.Leccion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

public class LeccionesAprendizajeEnLineaController {

    @FXML
    private TextField idDeLeccionApredizaje;
    @FXML
    private TextArea textAreaDeAprendizajeField;
    @FXML
    private TextField NombreDeLeccionApredizaje;

    @FXML
    public void initialize() {
    }

    @FXML
    public void buscarDeLeccionesAprendizajeOnAction(ActionEvent actionEvent) {
        try {
            int idCurso = Integer.parseInt(idDeLeccionApredizaje.getText());
            Curso curso = (Curso) Utility.cursosRegistrados.findNode(idCurso).data;
            String cursoNombre = curso.getNombre(); // Obtener el nombre del curso

            List<Leccion> listaLecciones = new ArrayList<>();

            for (int i = 0; i < curso.getLecciones().size(); i++) {
                Leccion leccion = (Leccion) curso.getLecciones().getList(i);
                leccion.setCursoNombre(cursoNombre); // Asignar el nombre del curso a cada lección
                listaLecciones.add(leccion);
            }

            mostrarLeccionesEnTextArea(listaLecciones);

        } catch (NumberFormatException e) {
            showAlert("Error", "El ID del curso debe ser un número entero.", Alert.AlertType.ERROR);
        } catch (NullPointerException e) {
            showAlert("Error", "Curso no encontrado.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al buscar la lección.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarLeccionesEnTextArea(List<Leccion> listaLecciones) {
        StringBuilder contenido = new StringBuilder();
        for (Leccion leccion : listaLecciones) {
            contenido.append("ID: ").append(leccion.getId()).append("\n");
            contenido.append("Nombre: ").append(leccion.getTitle()).append("\n");
            contenido.append("Contenido: ").append(leccion.getContent()).append("\n");
            contenido.append("Curso: ").append(leccion.getCursoNombre()).append("\n");
            contenido.append("---------------------------------------\n");
        }
        textAreaDeAprendizajeField.setText(contenido.toString());
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}