package controller;

import domain.clasesBase.BTreeNode;
import domain.clasesBase.Curso;
import domain.clasesBase.TreeException;
import domain.queue.QueueException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import static util.Utility.Bitacora;

public class EliminarCursosController {

    @FXML
    private TextField searchField;

    @FXML
    private BorderPane bp;

    @FXML
    private TableView<Curso> tableView;

    private ObservableList<Curso> cursosData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Curso, String> DuracionColumn;
    @FXML
    private TableColumn<Curso, String> DescripcionColumn;
    @FXML
    private TableColumn<Curso, String> nivelColumn;
    @FXML
    private TableColumn<Curso, String> nameColumn;
    @FXML
    private TableColumn<Curso, String> InstructorColumn;
    @FXML
    private TableColumn<Curso, String> idColumn;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        // Bind the columns to the attributes of Curso
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSiglas())));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        DescripcionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        DuracionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuracion())));
        nivelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDificultad()));
        InstructorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));

        // Set the items of the TableView to the observable list
        tableView.setItems(cursosData);
    }


    @FXML
    public void buscarMostrarCursos(ActionEvent actionEvent) {
        String cursoNombre = searchField.getText().trim();
        if (cursoNombre.isEmpty()) {
            mostrarAlerta("Error", "El campo de búsqueda está vacío");
            return;
        }

        Curso cursoEncontrado = null;

        try {
            BTreeNode node = Utility.cursosRegistrados.findNode(cursoNombre);
            if (node == null) {
                mostrarAlerta("Información", "El curso no está registrado");
                return;
            }
            cursoEncontrado = (Curso) node.data;
        } catch (TreeException e) {
            mostrarAlerta("Error", "Ocurrió un error al buscar el curso: " + e.getMessage());
            return;
        }

        if (cursoEncontrado != null) {
            actualizarTableView(cursoEncontrado);
        }
    }

    private void actualizarTableView(Curso curso) {
        cursosData.clear();
        cursosData.add(curso);
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

    @FXML
    public void eliminarCursosOnAction(ActionEvent actionEvent) throws QueueException {
        Bitacora.enQueue(Utility.UserActivo.getName() + "ha eliminado un Curso.");

        // Obtener el curso seleccionado en el TableView
        Curso cursoSeleccionado = tableView.getSelectionModel().getSelectedItem();

        // Si no se seleccionó ningún curso, mostrar una alerta
        if (cursoSeleccionado == null) {
            mostrarAlerta("Error", "No se ha seleccionado ningún curso para eliminar");
            return;
        }

        // Eliminar el curso de la lista observable
        cursosData.remove(cursoSeleccionado);

        // También puedes eliminar el curso del árbol si es necesario
        try {
            Utility.cursosRegistrados.remove(cursoSeleccionado);
        } catch (TreeException e) {
            mostrarAlerta("Error", "Ocurrió un error al eliminar el curso del árbol: " + e.getMessage());
        }

        // Refrescar el TableView
        tableView.refresh();

        // Mostrar una confirmación
        mostrarAlerta("Información", "El curso ha sido eliminado correctamente");
    }

}
