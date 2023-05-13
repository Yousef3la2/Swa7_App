module com.example.swa7_app {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.crypto;
    //requires mysql.connector.java;


    opens com.example.swa7_app to javafx.fxml;
    exports com.example.swa7_app;
}