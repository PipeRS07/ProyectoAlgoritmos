package controller;

import domain.bTree.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import domain.clasesBase.Inscripcion;
import util.Utility;

public class CancelInscripcionController {

    @FXML
    private TextField cursoTextField;

    @FXML
    public void handleCancelEnrollment(ActionEvent actionEvent) {
        // Método para manejar la cancelación de la inscripción
        String inscripcionSeleccionada = getSelectedInscripcion();

        if (inscripcionSeleccionada == null) {
            // Si no se ha seleccionado ninguna inscripción
            mostrarAlerta("Error", "Debe seleccionar una inscripción para cancelar.");
            return;
        }

        // Intentar remover la inscripción seleccionada de la lista de inscripciones solicitadas
        boolean exito;
        try {
            exito = Utility.inscripcionesSolicitadas.remove(inscripcionSeleccionada);
        } catch (TreeException e) {
            // Manejo de excepción en caso de error al acceder a la estructura de datos (BTree)
            mostrarAlerta("Error", "No se pudo acceder a la estructura de datos para cancelar la inscripción.");
            return;
        }

        if (exito) {
            // Si la cancelación fue exitosa, mostrar mensaje de éxito
            mostrarAlerta("Éxito", "Se ha cancelado la inscripción correctamente.");
            clearSelectedInscripcion(); // Limpiar la selección en el TextField
        } else {
            // Si no se pudo cancelar la inscripción, mostrar mensaje de error
            mostrarAlerta("Error", "No se pudo cancelar la inscripción seleccionada.");
        }
    }

    private String getSelectedInscripcion() {
        // Método para obtener la inscripción seleccionada del TextField
        // Aquí debes implementar cómo obtienes y devuelves el objeto Inscripcion desde cursoTextField
        // Dependiendo de cómo se enlace el TextField con las Inscripciones
        return cursoTextField.getText(); // Ajusta esta línea para obtener el objeto Inscripcion adecuadamente
    }

    private void clearSelectedInscripcion() {
        // Método para limpiar la selección actual en el TextField
        cursoTextField.clear(); // Limpiar el TextField después de una cancelación exitosa
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        // Método para mostrar una alerta con el título y mensaje dados
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
