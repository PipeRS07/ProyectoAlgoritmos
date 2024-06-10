package controller;

import Data.EnviarEmail;
import domain.clasesBase.User;
import domain.list.CircularDoublyLinkedList;
import domain.list.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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

public class RegistroGeneralController {
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
    @javafx.fxml.FXML
    private ComboBox<String> rol; // ComboBox para seleccionar el rol del usuario

    public void initialize() {
        rol.getItems().addAll(Ruta.USUINSTRUCTOR, Ruta.USUADMIN, Ruta.USUESTUDIANTE);
    }

    // Inicialización de la lista de usuarios registrados
    {
        Utility.usuariosRegistrados = new CircularDoublyLinkedList(); // Inicializa la lista de usuarios registrados
    }

    private CircularDoublyLinkedList circularDoublyLinkedList = new CircularDoublyLinkedList();

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para registrar un nuevo usuario
    @javafx.fxml.FXML
    public void RegisterUserOnAction(ActionEvent actionEvent) {
        // Obtener los valores del formulario
        String cedula = cedulaField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String selectedRol = rol.getValue(); // Obtener el rol seleccionado en el ComboBox

        // Validar que los campos no estén vacíos
        if (cedula.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || selectedRol == null) {
            showAlert("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Encriptar la contraseña
            String encryptedPassword = Encriptacion.obtenerContraseniaCifrada(password);

            // Crear un nuevo usuario
            User newUser = Fabrica.fabricaUsuarios(Integer.parseInt(cedula), username, email, selectedRol, encryptedPassword);

            // Verificar si la lista de usuarios registrados está vacía antes de intentar acceder a un nodo
            if (Utility.usuariosRegistrados.isEmpty()) {
                Utility.usuariosRegistrados.add(newUser);
                Utility.usuariosEnELSistema.add(newUser);
                EnviarEmail enviarEmail = new EnviarEmail();
                enviarEmail.enviarCorreoSinAdjunto(newUser.getEmail(), "Confirmacion de ingreso al sismtema", "¡Hola! " + newUser.getName() + "\nBienvenido a nuestra plataforma de aprendizaje!!");
                System.out.println("Usuario agregado correctamente a la lista de usuarios registrados: " + newUser);
                showAlert("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);
                //   enviarNotificacionRegistro(newUser, password);
                clearFields();
                return;
            }

            // Agregar el nuevo usuario a la lista de usuarios registrados
            Utility.usuariosRegistrados.add(newUser);
            Utility.usuariosEnELSistema.add(newUser);
            EnviarEmail enviarEmail = new EnviarEmail();
            enviarEmail.enviarCorreoSinAdjunto(newUser.getEmail(), "Confirmacion de ingreso al sismtema", "Bienvenido a nuestra plataforma de aprendizaje");
            System.out.println("Usuario agregado correctamente a la lista de usuarios registrados: " + newUser);
            showAlert("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);
            //  enviarNotificacionRegistro(newUser, password);

            clearFields();
        } catch (NoSuchAlgorithmException e) {
            showAlert("Error", "Hubo un problema al cifrar la contraseña", Alert.AlertType.ERROR);
        } catch (NumberFormatException e) {
            showAlert("Error", "La cédula debe ser un número entero", Alert.AlertType.ERROR);
        }
    }

    @javafx.fxml.FXML
    public void atrasOnAction(ActionEvent actionEvent) {
        loadPage("hello-view.fxml");
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
        rol.getSelectionModel().clearSelection(); // Limpiar la selección del ComboBox
    }
}
