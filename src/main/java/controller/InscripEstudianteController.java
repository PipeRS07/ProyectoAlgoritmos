package controller;

import Data.EnviarEmail;
import domain.bTree.BTree;
import domain.clasesBase.*;
import domain.graph.GraphException;
import domain.list.ListException;
import domain.queue.QueueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import util.Ruta;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

import static util.Utility.Bitacora;

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
    public void inscribirseOnAction(ActionEvent actionEvent) throws QueueException {
        Bitacora.enQueue(Utility.UserActivo.getName() + "Se ha inscrito a un curso.");

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

        if (!(Utility.UserActivo.getRole().equals(Ruta.USUESTUDIANTE))) {
            mostrarAlerta("Error", "Solo los estudiantes pueden inscribirse en los cursos");
            return;
        }

        Estudiante estudiante = (Estudiante) Utility.UserActivo;
        Inscripcion inscripcion = new Inscripcion(estudiante, curso);
        inscripcionesSolicitadas.add(inscripcion);
        try {
            curso.getInscripcionesEstudiantes().addVertex(inscripcion);
            curso.getInscripcionesEstudiantes().addEdge(curso, estudiante);
        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }

        // Envío de correo al estudiante
        enviarCorreoEstudiante(estudiante, curso);

        // Envío de notificación a administradores
        enviarNotificacionAdministradores(estudiante, curso);


        mostrarAlerta("Éxito", "Se ha inscrito correctamente en el curso: " + curso.getNombre());
    }

    private void enviarCorreoEstudiante(Estudiante estudiante, Curso curso) throws QueueException {
        Bitacora.enQueue(Utility.UserActivo.getName() + "Se ha enviado un correo");

        String correoEstudiante = estudiante.getEmail();
        String asunto = "Confirmación de Inscripción";
        String contenido = "Hola " + estudiante.getName() + ",\n\n"
                + "Te has inscrito correctamente en el curso " + curso.getNombre() + ".\n\n"
                + "Saludos,\n"
                + "Equipo de soporte.";

        EnviarEmail emailSender = new EnviarEmail();
        emailSender.enviarCorreoSinAdjunto(correoEstudiante, asunto, contenido);
    }

    private void enviarNotificacionAdministradores(Estudiante estudiante, Curso curso) throws QueueException {


        String asunto = "Nueva Inscripción en Curso";
        String contenido = "El estudiante " + estudiante.getName() + " se ha inscrito en el curso "
                + curso.getNombre() + ". Por favor, revisar y aprobar.";


        List<String> correosAdministradores = obtenerCorreosAdministradores();

        EnviarEmail emailSender = new EnviarEmail();
        for (String correoAdmin : correosAdministradores) {
            emailSender.enviarCorreoSinAdjunto(correoAdmin, asunto, contenido);
        }
    }

    private List<String> obtenerCorreosAdministradores() {

        List<String> correos = new ArrayList<>();
        correos.add("admin1@example.com");
        correos.add("admin2@example.com");
        return correos;
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
