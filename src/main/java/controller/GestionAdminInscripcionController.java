package controller;

import domain.bTree.BTree;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import domain.clasesBase.Inscripcion;
import domain.clasesBase.Curso;
import domain.clasesBase.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class GestionAdminInscripcionController {

    @FXML
    private TableView<Inscripcion> inscriptionsTable;

    @FXML
    private TableColumn<Inscripcion, String> statusColumn;

    @FXML
    private TableColumn<Inscripcion, Curso> courseColumn;

    @FXML
    private TableColumn<Inscripcion, String> dateColumn;

    @FXML
    private TableColumn<Inscripcion, Estudiante> userColumn;

    @FXML
    private ComboBox<String> filtrarComboBox;

    @FXML
    private TextField valorLabel;

    @FXML
    private Button filtrarButton;

    private List<Inscripcion> inscripcionesEstudiantes;

    // Suponiendo que inscripcionesSolicitadas es estática y accesible desde cualquier parte
    public static BTree inscripcionesSolicitadas;

    @FXML
    public void initialize() {
        // Obtener todas las inscripciones desde inscripcionesSolicitadas
        inscripcionesEstudiantes = new ArrayList<>(inscripcionesSolicitadas.getAll()); // Suponiendo que getAll() devuelve una lista de Inscripcion

        // Configurar opciones del ComboBox
        filtrarComboBox.getItems().addAll("Usuario", "Curso", "Estado");

        // Configurar las columnas de la tabla
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("curso"));
        // dateColumn.setCellValueFactory(new PropertyValueFactory<>("date")); // Si dateColumn está habilitada

        userColumn.setCellValueFactory(new PropertyValueFactory<>("estudiante"));

        // Configurar los ítems de la tabla con todas las inscripciones
        inscriptionsTable.setItems(FXCollections.observableArrayList(inscripcionesEstudiantes));
    }


    @FXML
    public void filtrarOnAction(ActionEvent actionEvent) {
        String filterType = filtrarComboBox.getValue();
        String filterValue = valorLabel.getText();
        List<Inscripcion> filteredList = new ArrayList<>();

        for (Inscripcion inscripcion : inscripcionesEstudiantes) {
            switch (filterType) {
                case "Usuario":
                    if (inscripcion.getEstudiante().getName().contains(filterValue)) {
                        filteredList.add(inscripcion);
                    }
                    break;
                case "Curso":
                    if (inscripcion.getCurso().getNombre().contains(filterValue)) {
                        filteredList.add(inscripcion);
                    }
                    break;
                case "Estado":
                    if (inscripcion.getStatus().equalsIgnoreCase(filterValue)) {
                        filteredList.add(inscripcion);
                    }
                    break;
            }
        }
        inscriptionsTable.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    public void rechazarOnAction(ActionEvent actionEvent) {
        Inscripcion inscripcionSeleccionada = inscriptionsTable.getSelectionModel().getSelectedItem();
        if (inscripcionSeleccionada != null) {
            inscripcionesEstudiantes.remove(inscripcionSeleccionada);
            inscriptionsTable.getItems().remove(inscripcionSeleccionada);
        }
    }

    @FXML
    public void approveOnAction(ActionEvent actionEvent) {
        Inscripcion inscripcionSeleccionada = inscriptionsTable.getSelectionModel().getSelectedItem();
        if (inscripcionSeleccionada != null && inscripcionSeleccionada.getStatus().equals("Pendiente")) {
            inscripcionSeleccionada.setStatus("Aprobado"); // Ajustar método setStatus() en Inscripcion
            inscriptionsTable.refresh();
        }
    }
}
