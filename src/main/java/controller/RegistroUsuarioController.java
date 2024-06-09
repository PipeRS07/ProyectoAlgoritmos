package controller;

import domain.clasesBase.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Fabrica;
import util.Utility;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class RegistroUsuarioController {
    @javafx.fxml.FXML
    private TextField usernameField;
    @javafx.fxml.FXML
    private TextField emailField;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private ChoiceBox<String> roleChoiceBox; // Añadir ChoiceBox para seleccionar el rol
    @javafx.fxml.FXML
    private BorderPane bp;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void RegisterUserOnAction(ActionEvent actionEvent) {
        // Obtener los valores del formulario
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validar que los campos no estén vacíos
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Encriptar la contraseña
            String encryptedPassword = Utility.obtenerContraseniaCifrada(password, "SHA-256");

            // Crear el nuevo usuario tipo Estudiante
            User newUser = Fabrica.fabricaUsuarios(Utility.getRandom(1000), username, email, "Estudiante", encryptedPassword);

            // Agregar el nuevo usuario a la lista CircularLinkedList
            Utility.usuariosEnELSistema.add(newUser);

            // Mostrar mensaje de éxito
            showAlert("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);

            // Limpiar los campos del formulario
            clearFields();
        } catch (NoSuchAlgorithmException e) {
            showAlert("Error", "Hubo un problema al cifrar la contraseña", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Hubo un problema al registrar el usuario", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void atrasOnAction(ActionEvent actionEvent) {
        loadPage("ingresoSistema.fxml");
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
        roleChoiceBox.setValue(null);
    }
}

