module com.example.autoschoolproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.autoschoolproject to javafx.fxml;
    exports com.example.autoschoolproject;
    exports DataBaseConnection;
    opens DataBaseConnection to javafx.fxml;
    exports com.example.autoschoolproject.controller;
    opens com.example.autoschoolproject.controller to javafx.fxml;
    opens com.example.autoschoolproject.classes to javafx.base;

}