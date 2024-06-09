package org.example.proyectoalgoritmos;

import domain.list.CircularDoublyLinkedList;
import domain.list.CircularLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Utility;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ingresoSistema.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        // Inicializar las listas de usuarios
        Utility.usuariosRegistrados = new CircularDoublyLinkedList();
        Utility.usuariosEnELSistema = new CircularLinkedList();
    }

    public static void main(String[] args) {
        launch();
    }
}