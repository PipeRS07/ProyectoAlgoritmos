package controller;

import domain.clasesBase.User;
import domain.list.CircularDoublyLinkedList;
import domain.list.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
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


public class RegistroUsuarioController {
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
    public void RegisterUserOnAction(ActionEvent actionEvent) {
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
            String encryptedPassword = Utility.obtenerContraseniaCifrada(password, "SHA-256");

            // Crear un nuevo usuario
            User newUser = new User(Integer.parseInt(cedula), username, email, encryptedPassword);

            // Agregar el nuevo usuario a las listas de usuarios registrados
            Utility.usuariosRegistrados.add(newUser);
            Utility.usuariosEnELSistema.add(newUser);

            // Verificar si el usuario se registró correctamente
            if (Utility.usuariosRegistrados.contains(newUser) && Utility.usuariosEnELSistema.contains(newUser)) {

                showAlert("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);
                clearFields();

                // Enviar notificación de registro por correo electrónico
                enviarNotificacionRegistro(newUser, password);
            } else {
                showAlert("Error", "El usuario no se ha registrado correctamente", Alert.AlertType.ERROR);
            }
        } catch (NoSuchAlgorithmException e) {
            showAlert("Error", "Hubo un problema al cifrar la contraseña", Alert.AlertType.ERROR);
        } catch (NumberFormatException e) {
            showAlert("Error", "La cédula debe ser un número entero", Alert.AlertType.ERROR);
        } catch (ListException e) {
            throw new RuntimeException(e);
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
        cedulaField.clear();
        usernameField.clear();
        emailField.clear();
        passwordField.clear();

    }


    private void enviarNotificacionRegistro(User usuario, String contraseniaOriginal) {
        String asunto = "Registro en la plataforma";
        String cuerpo = String.format(
                "Hola %s,\n\nTe has registrado exitosamente en la plataforma.\n\nFecha y hora: %s\nNombre de Usuario: %s\nUsuario (Cédula): %d\nContraseña: %s\n\nSaludos,\nEl equipo de la plataforma",
                usuario.getName(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                usuario.getName(),
                usuario.getId(), // Suponiendo que el ID es el número de cédula
                contraseniaOriginal
        );

        // Configuración del servidor de correo
        String host = "smtp.gmail.com"; // Host SMTP de Gmail
        String from = "veronicaagueroaguilar@gmail.com"; // Cambia esto a tu dirección de correo
        final String username = "veronicaagueroaguilar@gmail.com"; // Cambia esto a tu dirección de correo
        final String password = "tucontraseña"; // Cambia esto a tu contraseña o contraseña de aplicación

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail()));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);

            System.out.println("Notificación enviada a: " + usuario.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
