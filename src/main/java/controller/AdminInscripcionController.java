package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminInscripcionController {
    @javafx.fxml.FXML
    private TableView inscriptionsTable;
    @javafx.fxml.FXML
    private TextField filterInscriptionField;
    @javafx.fxml.FXML
    private TableColumn statusColumn;
    @javafx.fxml.FXML
    private TableColumn courseColumn;
    @javafx.fxml.FXML
    private TableColumn dateColumn;
    @javafx.fxml.FXML
    private TableColumn userColumn;

    @javafx.fxml.FXML
    public void handleFilterInscriptions(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleReject(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleApprove(ActionEvent actionEvent) {
    }
}
