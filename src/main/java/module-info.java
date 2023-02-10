module com.example.prog1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.prog1 to javafx.fxml;
    exports com.example.prog1;
    exports com.example.prog1.controller.grafico;
    opens com.example.prog1.controller.grafico to javafx.fxml;
    exports com.example.prog1.dbConnection;
    opens com.example.prog1.dbConnection to javafx.fxml;
}