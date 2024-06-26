package controller;

import domain.clasesBase.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;
import util.Ruta;
import util.Utility;

import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class ManagementCourseController {
    private User UserActivo;

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
    @javafx.fxml.FXML
    public void searchOnAction(ActionEvent actionEvent) {
        loadPage("buscarCursos.fxml");
    }

    @javafx.fxml.FXML
    public void editOnAction(ActionEvent actionEvent) {
        if(Utility.UserActivo.getRole().equals(Ruta.USUINSTRUCTOR)) {
      loadPage("editarCurso.fxml");
        } else {
            // Manejo del caso en que UserActivo es null o no tiene el rol esperado
            System.out.println("UserActivo es null o no tiene el rol adecuado.");
        }

    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        if(Utility.UserActivo.getRole().equals(Ruta.USUINSTRUCTOR)) {
        loadPage("eliminarCurso.fxml");
        } else {
            // Manejo del caso en que UserActivo es null o no tiene el rol esperado
            System.out.println("UserActivo es null o no tiene el rol adecuado.");
        }
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        if(Utility.UserActivo.getRole().equals(Ruta.USUINSTRUCTOR) ) {
            loadPage("crearCurso.fxml");
        } else {
             //Manejo del caso en que UserActivo es null o no tiene el rol esperado
            System.out.println("UserActivo es null o no tiene el rol adecuado.");
        }
    }

    @javafx.fxml.FXML
    public void showOnAction(ActionEvent actionEvent) {
        loadPage("mostrarCursos.fxml");
    }
}
