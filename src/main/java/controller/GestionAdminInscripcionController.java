package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionAdminInscripcionController {
    @javafx.fxml.FXML
    private ComboBox userFilterComboBox;
    @javafx.fxml.FXML
    private ComboBox courseFilterComboBox;
    @javafx.fxml.FXML
    private ComboBox statusFilterComboBox;
    @javafx.fxml.FXML
    private TableView inscriptionsTable;
    @javafx.fxml.FXML
    private TableColumn statusColumn;
    @javafx.fxml.FXML
    private TableColumn courseColumn;
    @javafx.fxml.FXML
    private TableColumn dateColumn;
    @javafx.fxml.FXML
    private DatePicker dateFilterTextField;
    @javafx.fxml.FXML
    private TableColumn userColumn;



    @javafx.fxml.FXML
    public void filtrarOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void rechazarOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void ApproveOnAction(ActionEvent actionEvent) {
    }
}
