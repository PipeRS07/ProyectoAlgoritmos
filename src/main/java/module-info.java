module org.example.proyectoalgoritmos {
    requires javafx.controls;
    requires javafx.fxml;
    requires activation;
    requires javax.mail;


    opens org.example.proyectoalgoritmos to javafx.fxml;
    exports org.example.proyectoalgoritmos;
    exports controller;
    opens controller to javafx.fxml;

    requires javafx.graphics;
    requires itextpdf;

    opens domain.clasesBase to javafx.base;


}