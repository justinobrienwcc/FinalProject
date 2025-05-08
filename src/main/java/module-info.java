module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;  // allows application to use java.sql

    requires org.kordamp.bootstrapfx.core;

    opens com.example.finalproject to javafx.fxml;
    exports com.example.finalproject;
}