package ce.client;

import ce.client.Windows.MainWindow;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Breakout");
        stage.getIcons().add(new Image("file:scr/img/icon.png"));
        MainWindow.launch(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}