package controller;

import domain.bTree.TreeException;
import domain.clasesBase.Leccion;
import domain.clasesBase.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Utility;

import java.io.IOException;

public class LeccionesMantenimientoController {
//    @FXML
//    private BorderPane bp;
//    @FXML
//    private TextField nombreCreaLeccionesField;
//    @FXML
//    private TextField contenidoCreaLeccionesField;
//    @FXML
//    private TextField IdCrearLeccionField;
//    @FXML
//    private TextField nombreModificarLeccionesField;
//    @FXML
//    private PasswordField contenidoModificarLeccionesField;
//    @FXML
//    private TextField idModificarLeccionesField;
//    @FXML
//    private TextField busquedaLeccionField;
//    @FXML
//    private TextField buscaLeccionField;
//    @FXML
//    private TableView<Leccion> tableViewLecciones;
//
//    private void showAlert(String title, String content) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//
//    @FXML
//    public void actualizarLeccion(ActionEvent actionEvent) {
//        try {
//            int id = Integer.parseInt(idModificarLeccionesField.getText());
//            Leccion leccion = (Leccion) Utility.leccionesRegistradas.(id);
//            if (leccion != null) {
//                leccion.setTitle(nombreModificarLeccionesField.getText());
//                leccion.setContent(contenidoModificarLeccionesField.getText());
//                showAlert("Éxito", "Lección actualizada correctamente");
//            } else {
//                showAlert("Error", "Lección no encontrada");
//            }
//        } catch (TreeException | NumberFormatException e) {
//            showAlert("Error", "ID inválido o lección no encontrada");
//        }
//    }
//
//    @FXML
//    public void agregarLeccion(ActionEvent actionEvent) {
//        try {
//            int id = Integer.parseInt(IdCrearLeccionField.getText());
//            String nombre = nombreCreaLeccionesField.getText();
//            String contenido = contenidoCreaLeccionesField.getText();
//
//            Leccion nuevaLeccion = new Leccion(id, nombre, contenido);
//            Utility.leccionesRegistradas.add(nuevaLeccion);
//
//            showAlert("Éxito", "Lección agregada correctamente");
//        } catch (NumberFormatException e) {
//            showAlert("Error", "Error al agregar la lección");
//        }
//    }
//
//    @FXML
//    public void eliminarLeccion(ActionEvent actionEvent) {
//        try {
//            int id = Integer.parseInt(busquedaLeccionField.getText());
//            Utility.leccionesRegistradas.remove(id);
//            showAlert("Éxito", "Lección eliminada correctamente");
//        } catch (NumberFormatException e) {
//            showAlert("Error", "ID inválido o lección no encontrada");
//        } catch (domain.clasesBase.TreeException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @FXML
//    public void buscarLeccion(ActionEvent actionEvent) {
//        try {
//            int id = Integer.parseInt(buscaLeccionField.getText());
//            Leccion leccion = (Leccion) Utility.leccionesRegistradas.search(id);
//            if (leccion != null) {
//                showAlert("Resultado de la búsqueda", "Lección encontrada: " + leccion.getTitle() + " - " + leccion.getContent());
//            } else {
//                showAlert("Resultado de la búsqueda", "Lección no encontrada");
//            }
//        } catch (TreeException | NumberFormatException e) {
//            showAlert("Error", "ID inválido o lección no encontrada");
//        }
//    }
}