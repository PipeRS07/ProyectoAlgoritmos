module org.example.proyectoalgoritmos {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.proyectoalgoritmos to javafx.fxml;
    exports org.example.proyectoalgoritmos;
}