package controller;

import domain.clasesBase.User;
import domain.list.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Fabrica;
import util.Utility;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class inicioSecionController {
    @javafx.fxml.FXML
    private TextField usernameField;
    @javafx.fxml.FXML
    private PasswordField passwordField;
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
    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }

    @javafx.fxml.FXML
    public void RegisterUserOnAction(ActionEvent actionEvent) {

        //la bandera permite saber si el usuario esta registrado para poder cargarle la pagina
        boolean bandera=false;
        try {
            String contrasenia = util.Encriptacion.obtenerContraseniaCifrada(passwordField.getText());
            String name = this.usernameField.getText();
            User aux = null;
            //comparo entre todos los usuarios registrados si existe uno con la misma contraseña y el mismo usuario
            for (int i = 0; i < Utility.usuariosRegistrados.size(); i++) {


                aux=(User) Utility.usuariosRegistrados.getNode(i+1).data;

                if(Integer.toString(aux.getId()).equals(name) && aux.getContrasenia().equals(contrasenia)){
                    Fabrica.fabricaUsuarios(aux);
                    Utility.usuariosEnELSistema.add(aux);
                    Utility.UserActivo =aux;
                    bandera=true;
                }
            }
            clearFields();
        }catch (ListException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //valido si el usuario y la contraseña corresponden a un usuario registrado para cargar la pagina
        if(bandera)
            loadPage("hello-view.fxml");
    }

}
