package controller;

import domain.clasesBase.BST;
import domain.clasesBase.Curso;
import domain.clasesBase.Leccion;
import domain.bTree.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import util.Utility;

import static util.Utility.leccionesRegistradas;

public class LeccionesMantenimientoController {

    @FXML
    private TableView<Leccion> tableViewLecciones;
    @FXML
    private TextField idModificarLeccionField;
    @FXML
    private TextField contenidoCreaNuevaLeccionField;
    @FXML
    private TextField nombreModificarLeccionField;
    @FXML
    private PasswordField contenidoModificarLeccionField;
    @FXML
    private TextField IdCursoCrearLeccionField;
    @FXML
    private TextField idBuscaLeccionField;
    @FXML
    private BorderPane bp;
    @FXML
    private TextField nombreCreaNuevaLeccionField;
    @FXML
    private TextField idCrearNuevaLeccionField;

    @FXML
    public void agregarLeccionAlCursoOnAction(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idCrearNuevaLeccionField.getText());
            String nombre = nombreCreaNuevaLeccionField.getText();
            String contenido = contenidoCreaNuevaLeccionField.getText();
            Leccion nuevaLeccion = new Leccion(id, nombre, contenido);

            Curso curso = (Curso) Utility.cursosRegistrados.findNode(id).data;

            curso.getLecciones().add(nuevaLeccion);

            System.out.println(curso);

            tableViewLecciones.getItems().add(nuevaLeccion);

            clearFields();
            showAlert("Lección agregada", "La lección se ha agregado correctamente.", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            showAlert("Error", "El ID debe ser un número entero.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al agregar la lección.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void eliminarLeccionOnAction(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idBuscaLeccionField.getText());
            Leccion leccion = findLeccionById(id);

            if (leccion != null) {
                leccionesRegistradas.remove(leccion);
                tableViewLecciones.getItems().remove(leccion);
                showAlert("Lección eliminada", "La lección se ha eliminado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("No encontrado", "No se encontró una lección con ese ID.", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "El ID debe ser un número entero.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al eliminar la lección.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void actualizarLeccionOnAction(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idModificarLeccionField.getText());
            Leccion leccion = findLeccionById(id);

            if (leccion != null) {
                leccion.setTitle(nombreModificarLeccionField.getText());
                leccion.setContent(contenidoModificarLeccionField.getText());
                tableViewLecciones.refresh();
                showAlert("Lección actualizada", "La lección se ha actualizado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("No encontrado", "No se encontró una lección con ese ID.", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "El ID debe ser un número entero.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al actualizar la lección.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buscarLeccionOnAction(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idBuscaLeccionField.getText());
            Leccion leccion = findLeccionById(id);

            if (leccion != null) {
                tableViewLecciones.getItems().clear();
                tableViewLecciones.getItems().add(leccion);
            } else {
                showAlert("No encontrado", "No se encontró una lección con ese ID.", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "El ID debe ser un número entero.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al buscar la lección.", Alert.AlertType.ERROR);
        }
    }

    private Leccion findLeccionById(int id) throws TreeException, domain.clasesBase.TreeException {
        for (Object obj : leccionesRegistradas.inOrder().split(", ")) {
            Leccion leccion = (Leccion) obj;
            if (leccion.getId() == id) {
                return leccion;
            }
        }
        return null;
    }

    private void clearFields() {
        idCrearNuevaLeccionField.clear();
        nombreCreaNuevaLeccionField.clear();
        contenidoCreaNuevaLeccionField.clear();
        IdCursoCrearLeccionField.clear();
        idModificarLeccionField.clear();
        nombreModificarLeccionField.clear();
        contenidoModificarLeccionField.clear();
        idBuscaLeccionField.clear();
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}