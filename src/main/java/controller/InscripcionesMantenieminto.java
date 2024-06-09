package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.example.proyectoalgoritmos.HelloApplication;

import java.io.IOException;

public class InscripcionesMantenieminto {
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
    public void handleSearchCourses(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleFilterInscriptions(ActionEvent actionEvent) {
    }

    @Deprecated
    public void handleExit(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleReject(ActionEvent actionEvent) {
    }

    @Deprecated
    public void handleApproveInscriptions(ActionEvent actionEvent) {
    }

    @Deprecated
    public void handleViewInscriptions(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleApprove(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleEnroll(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleCancelEnrollment(ActionEvent actionEvent) {
    }
}
