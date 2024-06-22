package controller;

import domain.bTree.BTree;
import domain.clasesBase.BTreeNode;
import domain.clasesBase.Curso;
import domain.clasesBase.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

public class InscripEstudianteController {
    @FXML
    private TextField searchCoursesField;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private BorderPane bp;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ComboBox<String> difficultyComboBox;

    public static BTree inscripcionesSolicitadas = new BTree();

    @FXML
    public void initialize() {
            difficultyComboBox.getItems().addAll("low", "medium", "high");
    }

    @FXML
    public void BuscarOnAction(ActionEvent actionEvent) {

        String nombre = searchCoursesField.getText().trim();
        String categoria = categoryComboBox.getValue();
        String nivel = difficultyComboBox.getValue();

        if (nombre.isEmpty() && categoria == null && nivel == null) {
            mostrarAlerta("Error", "Debe especificar al menos un criterio de búsqueda");
            return;
        }

        List<Curso> cursosEncontrados = buscarCursos(nombre, categoria, nivel);

        if (cursosEncontrados.isEmpty()) {
            mostrarAlerta("Información", "No se encontraron cursos que coincidan con los criterios de búsqueda");
        } else {
            courseComboBox.getItems().clear();
            for (Curso curso : cursosEncontrados) {
                courseComboBox.getItems().add(curso.getNombre());
            }
        }
    }

    @FXML
    public void inscribirseOnAction(ActionEvent actionEvent) {
        String cursoSeleccionado = courseComboBox.getValue();
        if (cursoSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un curso para inscribirse");
            return;
        }

        Curso curso = obtenerCurso(cursoSeleccionado);
        if (curso == null) {
            mostrarAlerta("Error", "El curso seleccionado no se encontró");
            return;
        }

        inscripcionesSolicitadas.add(curso);
        System.out.println("Listoooo");
        mostrarAlerta("Éxito", "Se ha inscrito correctamente en el curso: " + curso.getNombre());
    }

    private List<Curso> buscarCursos(String nombre, String categoria, String nivel) {
        List<Curso> cursosEncontrados = new ArrayList<>();

        try {
            //  método para obtener el nodo del curso por nombre
            BTreeNode node = Utility.cursosRegistrados.findNode(nombre);
            if (node != null) {
                Curso curso = (Curso) node.data;
                if ((categoria == null || curso.getDuracion().equals(categoria)) &&
                        (nivel == null || curso.getDificultad().equals(nivel))) {
                    cursosEncontrados.add(curso);
                }
            }
        } catch (TreeException e) {
            mostrarAlerta("Error", "Ocurrió un error al buscar los cursos: " + e.getMessage());
        }

        return cursosEncontrados;
    }

    private Curso obtenerCurso(String nombre) {
        try {
            BTreeNode node = Utility.cursosRegistrados.findNode(nombre);
            if (node != null) {
                return (Curso) node.data;
            }
        } catch (TreeException e) {
            mostrarAlerta("Error", "Ocurrió un error al buscar el curso: " + e.getMessage());
        }
        return null;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}
