package controller;

import domain.bTree.TreeException;
import domain.clasesBase.BTreeNode;
import domain.clasesBase.Curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.List;


public class MostrarCursosController {
    @javafx.fxml.FXML
    private TableView<Curso> tableView;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField searchField;


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

        tableView.getColumns().addAll(idColumn, nameColumn, descriptionColumn, durationColumn, difficultyColumn, siglasColumn);
    }

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void atrasDeMostrarCursosOnAction(ActionEvent actionEvent) {
        loadPage("managementCourse.fxml");
    }

    @javafx.fxml.FXML
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

    private void actualizarTableView(Curso curso) {
        ObservableList<Curso> cursosObservableList = FXCollections.observableArrayList();
        cursosObservableList.add(curso);
        tableView.setItems(cursosObservableList);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
