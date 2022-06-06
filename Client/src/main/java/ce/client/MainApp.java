package ce.client;

import ce.client.Windows.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainWindow.launch(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}