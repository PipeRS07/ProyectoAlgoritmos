package controller;

import domain.clasesBase.BST;
import domain.clasesBase.Leccion;
import domain.bTree.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import util.Utility;

import static util.Utility.leccionesRegistradas;

public class LeccionesMantenimientoController {
    @FXML
    private BorderPane bp;
    @FXML
    private TextField nombreCreaLeccionesField;
    @FXML
    private TextField contenidoCreaLeccionesField;
    @FXML
    private TextField nombreModificarLeccionesField;
    @FXML
    private PasswordField contenidoModificarLeccionesField;
    @FXML
    private TextField idModificarLeccionesField;
    @FXML
    private TableView<Leccion> tableViewLecciones;
    @FXML
    private TextField busquedaLeccionField;
    @FXML
    private TextField buscaLeccionField;
    @FXML
    private TextField IdCrearLeccionField;

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void eliminarLeccionOnAction(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idModificarLeccionesField.getText());
            Leccion leccion = new Leccion(id, "", ""); // Creamos una lección solo con el ID para buscarla

            if (leccionesRegistradas.contains(leccion)) {
                leccionesRegistradas.remove(leccion);
                showAlert("Eliminación Exitosa", "La lección se eliminó correctamente.");
            } else {
                showAlert("Error", "La lección no existe.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "No se pudo eliminar la lección: " + e.getMessage());
        } catch (domain.clasesBase.TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void actualizarLeccionOnAction(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idModificarLeccionesField.getText());
            String nuevoNombre = nombreModificarLeccionesField.getText();
            String nuevoContenido = contenidoModificarLeccionesField.getText();

            Leccion leccion = new Leccion(id, nuevoNombre, nuevoContenido);
            leccionesRegistradas.remove(leccion); // Eliminamos la lección actual
            leccionesRegistradas.add(leccion);    // Agregamos la lección actualizada

            showAlert("Actualización Exitosa", "La lección se actualizó correctamente.");
        } catch (NumberFormatException e) {
            showAlert("Error", "No se pudo actualizar la lección: " + e.getMessage());
        } catch (domain.clasesBase.TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void agregarLeccionOnAction(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(IdCrearLeccionField.getText());
            String nombre = nombreCreaLeccionesField.getText();
            String contenido = contenidoCreaLeccionesField.getText();

            Leccion leccion = new Leccion(id, nombre, contenido);
            leccionesRegistradas.add(leccion);

            showAlert("Lección Agregada", "La lección se agregó correctamente.");
        } catch (NumberFormatException e) {
            showAlert("Error", "No se pudo agregar la lección: " + e.getMessage());
        }
    }

    @FXML
    public void buscarLeccionOnAction(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idModificarLeccionesField.getText());
            Leccion leccion = new Leccion(id, "", ""); // Creamos una lección solo con el ID para buscarla

            if (leccionesRegistradas.contains(leccion)) {
                nombreModificarLeccionesField.setText(leccion.getTitle());
                contenidoModificarLeccionesField.setText(String.valueOf(leccion.getContent()));
                showAlert("Búsqueda Exitosa", "Se encontró la lección.");
            } else {
                showAlert("Error", "No se encontró la lección.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "No se pudo realizar la búsqueda: " + e.getMessage());
        } catch (domain.clasesBase.TreeException e) {
            throw new RuntimeException(e);
        }
    }
}