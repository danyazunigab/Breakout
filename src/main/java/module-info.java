module ce.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens ce.client to javafx.fxml;
    exports ce.client;
}