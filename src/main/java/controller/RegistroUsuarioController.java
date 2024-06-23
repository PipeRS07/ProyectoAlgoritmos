package controller;

import Data.EnviarEmail;
import domain.clasesBase.User;
import domain.list.CircularDoublyLinkedList;
import domain.list.ListException;
import domain.queue.QueueException;
import javafx.event.ActionEvent;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import static util.Utility.Bitacora;

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
    public void RegisterUserOnAction(ActionEvent actionEvent) throws QueueException {
        Bitacora.enQueue(Utility.UserActivo.getName() + "Se ha registrado un Usuario Nuevo");

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
        String encryptedPassword = null;
        try {
            encryptedPassword = Encriptacion.obtenerContraseniaCifrada(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // Crear un nuevo usuario

        User newUser = Fabrica.fabricaUsuarios(Integer.parseInt(cedula), username, email, Ruta.USUESTUDIANTE, encryptedPassword);
        System.out.println(newUser);
        email=this.emailField.getText();
        try {

//            // Verificar si la lista de usuarios registrados está vacía antes de intentar acceder a un nodo
//            if (Utility.usuariosRegistrados.isEmpty()) {
//                Utility.usuariosRegistrados.add(newUser);
//                Utility.usuariosEnELSistema.add(newUser);
//
//                // Obtener la fecha y hora actual del momento en que se envía el correo
//                LocalDateTime now = LocalDateTime.now();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//                String formattedDate = now.format(formatter);
//
//                EnviarEmail enviarEmail = new EnviarEmail();
//                enviarEmail.enviarCorreoSinAdjunto(email,
//                        "Confirmacion de ingreso al sistema",
//                        "¡Hola! " + newUser.getName() +
//                                "\nUsuario: " + newUser.getId() +
//                                "\nTu contraseña: " + password +
//                                "\nFecha y hora: " + formattedDate +
//                                "\nBienvenido a nuestra plataforma de aprendizaje!!");
//
//                System.out.println("Usuario agregado correctamente a la lista de usuarios registrados: " + newUser);
//                showAlert("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);
//                // enviarNotificacionRegistro(newUser, password);
//                clearFields();
//                return;
//            }

            // Agregar el nuevo usuario a la lista de usuarios registrados
            Utility.usuariosRegistrados.add(newUser);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = now.format(formatter);
            Utility.usuariosEnELSistema.add(newUser);
            EnviarEmail enviarEmail = new EnviarEmail();
            enviarEmail.enviarCorreoSinAdjunto(email,
                    "Confirmacion de ingreso al sistema",
                    "¡Hola! " + newUser.getName() +
                            "\nUsuario: " + newUser.getId() +
                            "\nTu contraseña: " + password +
                            "\nFecha y hora: " + formattedDate +
                            "\nBienvenido a nuestra plataforma de aprendizaje!!");

            System.out.println("Usuario agregado correctamente a la lista de usuarios registrados: " + newUser);
            showAlert("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);
            //  enviarNotificacionRegistro(newUser, password);

            clearFields();
        }  catch (NumberFormatException e) {
            showAlert("Error", "La cédula debe ser un número entero", Alert.AlertType.ERROR);
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