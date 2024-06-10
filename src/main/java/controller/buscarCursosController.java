package controller;

import domain.bTree.AVLTree;
import domain.clasesBase.BTreeNode;
import domain.clasesBase.Curso;
import domain.clasesBase.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private BorderPane bp;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        TableColumn<Curso, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Curso, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Curso, String> descriptionColumn = new TableColumn<>("Descripción");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Curso, String> durationColumn = new TableColumn<>("Duración");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duracion"));

        TableColumn<Curso, String> difficultyColumn = new TableColumn<>("Dificultad");
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("dificultad"));

        TableColumn<Curso, String> siglasColumn = new TableColumn<>("Siglas");
        siglasColumn.setCellValueFactory(new PropertyValueFactory<>("siglas"));

        cursosTableView.getColumns().addAll(idColumn, nameColumn, descriptionColumn, durationColumn, difficultyColumn, siglasColumn);
    }

    @FXML
    public void buscarMostrarCursos(ActionEvent actionEvent) {
        System.out.println(Utility.cursosRegistrados);

        String cursoNombre = searchField.getText().trim();
        if (cursoNombre.isEmpty()) {
            mostrarAlerta("Error", "El campo de búsqueda está vacío");
            return;
        }

        Curso cursoEncontrado = null;
        try {
            cursoEncontrado = (Curso) Utility.cursosRegistrados.findNode(cursoNombre).data;
           // System.out.println(cursoEncontrado);
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
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