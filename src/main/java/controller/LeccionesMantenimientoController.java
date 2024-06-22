package controller;

import domain.clasesBase.BST;
import domain.clasesBase.Curso;
import domain.clasesBase.Leccion;
import domain.bTree.TreeException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

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
    private TextField  contenidoModificarLeccionField;
    @FXML
    private TextField IdCursoCrearLeccionField;
    @FXML
    private BorderPane bp;
    @FXML
    private TextField nombreCreaNuevaLeccionField;
    @FXML
    private TextField idCrearNuevaLeccionField;
    @FXML
    private TextField idBuscaCursoField;

    @FXML
    private TableColumn<Leccion, Integer> colId;
    @FXML
    private TableColumn<Leccion, String> colNombre;
    @FXML
    private TableColumn<Leccion, String> colContenido;
    @FXML
    private TableColumn<Leccion, String> colCursoNombre; // Nueva columna para el nombre del curso

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("title"));
        colContenido.setCellValueFactory(new PropertyValueFactory<>("content"));
        colCursoNombre.setCellValueFactory(new PropertyValueFactory<>("cursoNombre")); // Configurar la nueva columna
    }

    @FXML
    public void agregarLeccionAlCursoOnAction(ActionEvent actionEvent) {
        try {
            int idLeccion = Integer.parseInt(idCrearNuevaLeccionField.getText());
            int idCurso = Integer.parseInt(this.IdCursoCrearLeccionField.getText());
            String nombre = nombreCreaNuevaLeccionField.getText();
            String contenido = contenidoCreaNuevaLeccionField.getText();

            Curso curso = (Curso) Utility.cursosRegistrados.findNode(idCurso).data;
            String cursoNombre = curso.getNombre(); // Obtener el nombre del curso

            Leccion nuevaLeccion = new Leccion(idLeccion, nombre, contenido, cursoNombre); // Pasar el nombre del curso
            curso.getLecciones().add(nuevaLeccion);

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
        Leccion leccionSeleccionada = tableViewLecciones.getSelectionModel().getSelectedItem();
        if (leccionSeleccionada != null) {
            try {
                int idCurso = Integer.parseInt(idBuscaCursoField.getText());
                Curso curso = (Curso) Utility.cursosRegistrados.findNode(idCurso).data;

                if (curso != null) {
                    curso.getLecciones().remove(leccionSeleccionada);
                    tableViewLecciones.getItems().remove(leccionSeleccionada);

                    // Refrescar el TableView
                    tableViewLecciones.refresh();

                    showAlert("Lección eliminada", "La lección se ha eliminado correctamente.", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Curso no encontrado.", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "El ID del curso debe ser un número entero.", Alert.AlertType.ERROR);
            } catch (Exception e) {
                showAlert("Error", "Ha ocurrido un error al eliminar la lección.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Error", "Selecciona una lección para eliminar.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void actualizarLeccionOnAction(ActionEvent actionEvent) {
        Leccion leccionSeleccionada = tableViewLecciones.getSelectionModel().getSelectedItem();
        if (leccionSeleccionada != null) {
            try {
                int idCurso = Integer.parseInt(idBuscaCursoField.getText());
                Curso curso = (Curso) Utility.cursosRegistrados.findNode(idCurso).data;

                if (curso != null) {
                    int idLeccion = Integer.parseInt(idModificarLeccionField.getText());
                    String nombre = nombreModificarLeccionField.getText();
                    String contenido = contenidoModificarLeccionField.getText();

                    leccionSeleccionada.setId(idLeccion);
                    leccionSeleccionada.setTitle(nombre);
                    leccionSeleccionada.setContent(contenido);

                    // Refrescar el TableView
                    tableViewLecciones.refresh();

                    showAlert("Lección actualizada", "La lección se ha actualizado correctamente.", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Curso no encontrado.", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "El ID del curso y la lección deben ser números enteros.", Alert.AlertType.ERROR);
            } catch (Exception e) {
                showAlert("Error", "Ha ocurrido un error al actualizar la lección.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Error", "Selecciona una lección para actualizar.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buscarLeccionOnAction(ActionEvent actionEvent) {
        try {
            int idCurso = Integer.parseInt(idBuscaCursoField.getText());
            Curso curso = (Curso) Utility.cursosRegistrados.findNode(idCurso).data;
            String cursoNombre = curso.getNombre(); // Obtener el nombre del curso

            List<Leccion> listaLecciones = new ArrayList<>();

            for (int i = 0; i < curso.getLecciones().size(); i++) {
                Leccion leccion = (Leccion) curso.getLecciones().getList(i);
                leccion.setCursoNombre(cursoNombre); // Asignar el nombre del curso a cada lección
                listaLecciones.add(leccion);
            }
            System.out.println("si busca" + listaLecciones);
            tableViewLecciones.setItems(FXCollections.observableArrayList(listaLecciones));

        } catch (NumberFormatException e) {
            showAlert("Error", "El ID del curso debe ser un número entero.", Alert.AlertType.ERROR);
        } catch (NullPointerException e) {
            showAlert("Error", "Curso no encontrado.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al buscar la lección.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        idCrearNuevaLeccionField.clear();
        IdCursoCrearLeccionField.clear();
        nombreCreaNuevaLeccionField.clear();
        contenidoCreaNuevaLeccionField.clear();
        idModificarLeccionField.clear();
        nombreModificarLeccionField.clear();
        contenidoModificarLeccionField.clear();
        idBuscaCursoField.clear();
    }
}
