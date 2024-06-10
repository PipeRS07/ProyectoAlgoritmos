package controller;

import Data.EnviarEmail;
import domain.clasesBase.User;
import domain.list.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import util.Encriptacion;
import util.Ruta;
import util.Utility;

import java.security.NoSuchAlgorithmException;

public class ActualizarUsuarioController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private BorderPane bp;
    @FXML
    private ComboBox<String> rol;
    @FXML
    private TextField buscar;
    @FXML
    private TextField contraseñaPerfilField;

    public void initialize() {
        rol.getItems().addAll(Ruta.USUINSTRUCTOR, Ruta.USUADMIN, Ruta.USUESTUDIANTE);
    }

    @FXML
    public void actualizarUsuarioOnAction(ActionEvent actionEvent) {
        // La bandera permite saber si el usuario está registrado para poder cargarle la página
        boolean bandera = false;
        try {
            String contrasenia = Encriptacion.obtenerContraseniaCifrada(contraseñaPerfilField.getText());
            String name = nameField.getText();
            String email = emailField.getText();

            // Buscar el usuario en la lista de usuarios registrados
            for (int i = 0; i < Utility.usuariosRegistrados.size(); i++) {
                User aux = (User) Utility.usuariosRegistrados.getNode(i + 1).data;
                if (aux.getId() == Integer.parseInt(buscar.getText())) {
                    // Remover el usuario existente de la lista
                    Utility.usuariosRegistrados.remove(aux);

                    // Actualizar los datos del usuario
                    aux.setName(name);
                    aux.setContrasenia(contrasenia);
                    aux.setEmail(email);
                    aux.setRole(rol.getValue());

                    // Agregar el usuario actualizado a la lista
                    Utility.usuariosRegistrados.add(aux);

                    // Envío de correo y demás operaciones...
                    EnviarEmail enviarEmail = new EnviarEmail();
                    enviarEmail.enviarCorreoSinAdjunto(aux.getEmail(), "Confirmacion de ingreso al sistema", "¡Hola! " + aux.getName() + "\nBienvenido a nuestra plataforma de aprendizaje!!");

                    // Establecer la bandera en true ya que el usuario ha sido actualizado
                    bandera = true;

                    // Mostrar mensaje de éxito
                    showAlert("Éxito", "Usuario actualizado correctamente", Alert.AlertType.INFORMATION);
                    break; // Salir del bucle ya que se ha encontrado y actualizado el usuario
                }
            }

            if (!bandera) {
                // Mostrar un mensaje si el usuario no se encontró
                showAlert("Error", "El usuario no se encuentra registrado", Alert.AlertType.ERROR);
            }
        } catch (ListException | NoSuchAlgorithmException e) {
            // Mostrar mensaje de error si ocurre una excepción
            showAlert("Error", "Hubo un problema al actualizar el usuario", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buscarOnAction(ActionEvent actionEvent) {
        try {
            int idBuscar = Integer.parseInt(buscar.getText());
            User usuarioEncontrado = null;

            // Buscar el usuario en la lista de usuarios registrados
            for (int i = 0; i < Utility.usuariosRegistrados.size(); i++) {
                User usuario = (User) Utility.usuariosRegistrados.getNode(i + 1).data;
                if (usuario.getId() == idBuscar) {
                    // Usuario encontrado
                    usuarioEncontrado = usuario;
                    break;
                }
            }

            if (usuarioEncontrado != null) {
                // Mostrar los datos del usuario encontrado en los campos correspondientes
                nameField.setText(usuarioEncontrado.getName());
                emailField.setText(usuarioEncontrado.getEmail());
                rol.setValue(usuarioEncontrado.getRole());
                // No mostrar la contraseña por motivos de seguridad
                contraseñaPerfilField.clear();
            } else {
                // Mostrar un mensaje de error si el usuario no se encontró
                showAlert("Error", "El usuario no se encuentra registrado", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException | ListException e) {
            // Mostrar un mensaje de error si el texto ingresado no es un número
            showAlert("Error", "Ingrese un ID válido", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // Elimina el encabezado por simplicidad
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        // Establecer el texto de todos los campos en una cadena vacía
        nameField.setText("");
        emailField.setText("");
        rol.setValue(null); // Limpiar la selección del ComboBox
        contraseñaPerfilField.setText("");
        buscar.setText("");
    }

}