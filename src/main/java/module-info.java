module ce.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens ce.client to javafx.fxml;
    opens ce.client.GameItems to com.fasterxml.jackson.databind;
    exports ce.client;
}