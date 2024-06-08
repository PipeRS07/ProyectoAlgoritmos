package controller;

import domain.clasesBase.User;
import domain.list.CircularLinkedList;
import domain.list.ListException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Fabrica;
import util.Ruta;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label messageLabel;

    private CircularLinkedList users;

    public LoginController() {
        users = new CircularLinkedList();
        initializeUsers();
    }

    /**
     * Inicializa los usuarios con roles y contraseñas encriptadas.
     */
    private void initializeUsers() {
        users.add(Fabrica.fabricaUsuarios(1, "admin", "admin@example.com", Ruta.USUADMIN, encryptPassword("admin123")));
        users.add(Fabrica.fabricaUsuarios(2, "instructor", "instructor@example.com", "Instructor", encryptPassword("instr123")));
        users.add(Fabrica.fabricaUsuarios(3, "usuario", "usuario@example.com", "Usuario", encryptPassword("user123")));
    }

    /**
     * Maneja el evento de inicio de sesión.
     */
    @FXML
    private void handleLogin() throws ListException {
        String email = usernameField.getText();
        String password = passwordField.getText();
        String encryptedPassword = encryptPassword(password);

        User user = findUserByEmail(email);

        if (user != null && user.getContrasenia().equals(encryptedPassword)) {
            messageLabel.setText("Bienvenido, " + user.getRole() + "!");
            messageLabel.setStyle("-fx-text-fill: green;");
            redirectToRoleView(user.getRole());
        } else {
            messageLabel.setText("Usuario o contraseña incorrectos.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Redirige al usuario a la vista correspondiente según su rol.
     */
    private void redirectToRoleView(String role) {
        try {
            FXMLLoader loader = new FXMLLoader();
            String fxmlFile = "";
            switch (role.toLowerCase()) {
                case "administrador":
                    fxmlFile = "/views/admin_view.fxml";
                    break;
                case "instructor":
                    fxmlFile = "/views/instructor_view.fxml";
                    break;
                case "usuario":
                    fxmlFile = "/views/user_view.fxml";
                    break;
                default:
                    throw new IllegalArgumentException("Rol desconocido: " + role);
            }

            loader.setLocation(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Obtén el stage actual
            Stage currentStage = (Stage) messageLabel.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Error al cargar la vista: " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Encripta la contraseña usando SHA-256.
     */
    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password;
        }
    }

    /**
     * Busca un usuario por su correo electrónico.
     */
    private User findUserByEmail(String email) throws ListException {
//        for (int i = 0; i < users.size(); i++) {
//            User user = (User) users.get(i);
//            if (user.getCorreo().equalsIgnoreCase(email)) {
//                return user;
//            }

        return null;
    }
}
