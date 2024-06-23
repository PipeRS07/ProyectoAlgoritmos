package controller;

import domain.clasesBase.User;
import domain.list.CircularDoublyLinkedList;
import domain.list.ListException;
import domain.queue.QueueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Encriptacion;
import util.Fabrica;
import util.Ruta;
import util.Utility;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import static util.Utility.Bitacora;

public class CrearUsuarioController {
    @javafx.fxml.FXML
    private TextField cedulaField; // Campo para el número de cédula
    @javafx.fxml.FXML
    private TextField usernameField; // Campo para el nombre de usuario
    @javafx.fxml.FXML
    private TextField emailField;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private BorderPane bp;

    // Inicialización de la lista de usuarios registrados


    private CircularDoublyLinkedList usuarios ;
    @FXML
    public void initialize() {
        usuarios= util.Utility.usuariosRegistrados;
    }

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para registrar un nuevo usuario
    public void RegisterUserOnAction(ActionEvent actionEvent) throws QueueException {
        Bitacora.enQueue(Utility.UserActivo.getName() + "ha registrado un nuevo Usuario.");

        // Obtener los valores del formulario
        String cedula = cedulaField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validar que los campos no estén vacíos
        if (cedula.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Encriptar la contraseña
            String encryptedPassword = Encriptacion.obtenerContraseniaCifrada(password);

            // Crear un nuevo usuario

            User newUser = Fabrica.fabricaUsuarios(Integer.parseInt(cedula), username, email, Ruta.USUESTUDIANTE, encryptedPassword);

            // Agregar el nuevo usuario a la lista de usuarios registrados
            usuarios.add(newUser);
            Utility.usuariosEnELSistema.add(newUser);
            System.out.println("Usuario agregado correctamente a la lista de usuarios registrados: " + newUser);
            showAlert("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);

            clearFields();
        } catch (NoSuchAlgorithmException e) {
            showAlert("Error", "Hubo un problema al cifrar la contraseña", Alert.AlertType.ERROR);
        } catch (NumberFormatException e) {
            showAlert("Error", "La cédula debe ser un número entero", Alert.AlertType.ERROR);
        }
    }


    @javafx.fxml.FXML
    public void atrasOnAction(ActionEvent actionEvent) {
        loadPage("menuUsuario.fxml");
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        cedulaField.clear();
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
    }
}