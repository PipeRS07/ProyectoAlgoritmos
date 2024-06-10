package controller;

import domain.clasesBase.BTreeNode;
import domain.clasesBase.Curso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Utility;

import java.io.IOException;

public class buscarCursosController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Curso> cursosTableView;
    @FXML
    private TableColumn colDescripcion;
    @FXML
    private TableColumn colSiglas;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colDuracion;
    @FXML
    private BorderPane bp;
    @FXML
    private TableColumn colDificultad;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void buscarMostrarCursos(ActionEvent actionEvent) {
        System.out.println(Utility.cursosRegistrados);

        String cursoNombre = searchField.getText().trim();
        if (cursoNombre.isEmpty()) {
            mostrarAlerta("Error", "El campo de búsqueda está vacío");
            return;
        }

        Curso cursoEncontrado = buscarCursoPorNombre(cursoNombre, Utility.cursosRegistrados.root);
        if (cursoEncontrado != null) {
            actualizarTableView(cursoEncontrado);
        } else {
            mostrarAlerta("Información", "El curso no está registrado");
        }
    }

    // Método recursivo para buscar un curso por nombre en el AVL
    private Curso buscarCursoPorNombre(String nombre, BTreeNode node) {
        if (node == null) {
            return null;
        }

        Curso curso = (Curso) node.data;
        int comparacion = nombre.compareToIgnoreCase(curso.getNombre());

        if (comparacion == 0) {
            return curso;
        } else if (comparacion < 0) {
            return buscarCursoPorNombre(nombre, node.left);
        } else {
            return buscarCursoPorNombre(nombre, node.right);
        }
    }

    //actualiza curso que encuentra
    private void actualizarTableView(Curso curso) {
        cursosTableView.getItems().clear();
        cursosTableView.getItems().add(curso);
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void atrasBuscarCursosOnAction(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }
}
