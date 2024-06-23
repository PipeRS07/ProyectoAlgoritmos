package controller;

import domain.clasesBase.User;
import domain.list.DoublyLinkedList;
import domain.list.ListException;
import domain.queue.QueueException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Utility;

import static util.Utility.Bitacora;

public class MostrarUsuarioController {
    @FXML
    private TextField busquedaField;
    @FXML
    private TableView<User> tableView;

    private ObservableList<User> usuariosData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, String> cedulaColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    private Alert alert;

    @FXML
    public void initialize() {
        // Enlaza las columnas con los atributos de User
        cedulaColumn.setCellValueFactory(cellData -> {
            int cedula = cellData.getValue().getId();
            return new SimpleStringProperty(String.valueOf(cedula));
        });
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        // Agrega los datos de la lista usuariosRegistrados al TableView
        tableView.setItems(usuariosData);
    }

    // Método para recibir la lista de usuarios desde otro controlador o clase
    public void mostrarUsuarios(ObservableList<User> usuarios) {
        usuariosData.addAll(usuarios);
    }

    @FXML
    public void eliminarUsuario(ActionEvent actionEvent) throws QueueException {
        Bitacora.enQueue(Utility.UserActivo.getName() + "Se ha eliminado un Usuario");

        // Obtener el usuario seleccionado en el TableView
        User usuarioSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            // Eliminar el usuario de la lista de datos y actualizar la tabla
            usuariosData.remove(usuarioSeleccionado);

            // También eliminar el usuario de la lista de usuarios registrados
            try {
                for (int i = 0; i < Utility.usuariosRegistrados.size(); i++) {
                    User usuario = (User) Utility.usuariosRegistrados.getNode(i +1).data;
                    System.out.println(usuario+"Usuario++++++++++++");
                    if (usuario.getId() == usuarioSeleccionado.getId()) {
                        Utility.usuariosRegistrados.remove(usuario);
                        break;
                    }
                }
            } catch (ListException e) {
                showAlert("Error", "No se pudo eliminar el usuario de la lista", Alert.AlertType.ERROR);
            }
        } else {
            // Muestra un mensaje de error si no se seleccionó ningún usuario
            showAlert("Error", "Debe seleccionar un usuario para eliminar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buscarOnAction(ActionEvent actionEvent) {
        try {
            int idBuscar = Integer.parseInt(busquedaField.getText());
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
                // Limpiar la tabla antes de mostrar el resultado de la búsqueda
                usuariosData.clear();
                // Agregar el usuario encontrado a la tabla
                usuariosData.add(usuarioEncontrado);
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
}
