package controller;

import domain.clasesBase.AVL;
import domain.clasesBase.User;
import domain.list.CircularDoublyLinkedList;
import domain.list.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Fabrica;
import util.Utility;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ingresoSistemaController {
    @javafx.fxml.FXML
    private TextField usernameField;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private Label messageLabel;
    private CircularDoublyLinkedList usuarios;
    private AVL cursos;
    @FXML
    public void initialize() {
        usuarios= util.Utility.usuariosRegistrados;
        cursos = Utility.cursosRegistrados;
    }

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void registrarseOnAction(ActionEvent actionEvent) {
        loadPage("registrarUsuario.fxml");
    }

    @javafx.fxml.FXML
    public void inicioSecionnAction(ActionEvent actionEvent) throws ListException {

     //la bandera permite saber si el usuario esta registrado para poder cargarle la pagina
        boolean bandera=false;
        try {
            String contrasenia = util.Encriptacion.obtenerContraseniaCifrada(passwordField.getText());
            String idText = this.usernameField.getText();

            User aux ;
            int size =usuarios.size();


       //comparo entre todos los usuarios registrados si existe uno con la misma contraseña y el mismo usuario
            for (int i = 0; i < size; i++) {

                aux=(User) usuarios.getNode(i+1).data;

                if(Integer.toString(aux.getId()).equals(idText) && aux.getContrasenia().equals(contrasenia)){
                    Fabrica.fabricaUsuarios(aux);
                    util.Utility.usuariosEnELSistema.add(aux);
                    Utility.UserActivo =aux;
                    bandera=true;

                }
            }
        }catch (ListException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //valido si el usuario y la contraseña corresponden a un usuario registrado para cargar la pagina
        if(bandera) {
            loadPage("hello-view.fxml");
        }


    }
}
