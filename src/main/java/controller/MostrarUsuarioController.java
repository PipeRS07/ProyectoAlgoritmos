package controller;

import domain.clasesBase.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    public void eliminarUsuario(ActionEvent actionEvent) {
        // Aquí puedes obtener el usuario seleccionado en el TableView y eliminarlo de la lista y de la tabla
        User usuarioSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            // Elimina el usuario de la lista de datos y actualiza la tabla
            usuariosData.remove(usuarioSeleccionado);
        } else {
            // Muestra un mensaje de error si no se seleccionó ningún usuario
            //showAlert("Error", "Debe seleccionar un usuario para eliminar", Alert.AlertType.ERROR);
        }
    }
}
