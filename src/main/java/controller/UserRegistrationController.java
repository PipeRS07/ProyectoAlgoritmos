package controller;

import domain.clasesBase.User;
import domain.list.CircularDoublyLinkedList;
import domain.list.ListException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import util.Fabrica;

public class UserRegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    private CircularDoublyLinkedList users;

    public UserRegistrationController() {
        users = new CircularDoublyLinkedList();
    }

    @FXML
    private void handleRegisterUser() throws ListException {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            showAlert(AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        // Aquí se puede añadir la lógica para verificar si el usuario ya existe
        User existingUser = findUserByEmail(email);
        if (existingUser != null) {
            showAlert(AlertType.ERROR, "Error", "El correo electrónico ya está registrado.");
            return;
        }

        // Encriptar la contraseña
        String encryptedPassword = encryptPassword(password);

        // Crear el nuevo usuario usando la fábrica y añadirlo a la lista
        User newUser = Fabrica.fabricaUsuarios(users.size() + 1, username, email, role, encryptedPassword);
        Fabrica.fabricaTDA(users, newUser);

        showAlert(AlertType.INFORMATION, "Registro exitoso", "El usuario ha sido registrado exitosamente.");
    }

    private String encryptPassword(String password) {
        // Implementa la lógica de encriptación aquí
        return password; // Ejemplo, cambiar por la lógica real de encriptación
    }

    private User findUserByEmail(String email) throws ListException {
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.getFirst();
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
