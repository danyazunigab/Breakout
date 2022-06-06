package ce.client.Windows;

import ce.client.Scenes.GameScene;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainWindow {

    private static GameScene gameScene;
    public static void launch(Stage window){

        window.setTitle("Breakout");
        Group root = new Group();
        Scene scene = new Scene(root,400,350, Color.web("#112B3C"));
        window.setScene(scene);

        Group game = new Group();
        int[][] testBlockMatrix = new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}
        };
        int[] testBarList = new int[] {1,2,3};
        gameScene = new GameScene(game,testBlockMatrix,testBarList);
        Button playButton = new Button("Play");
        playButton.setLayoutX(190);
        playButton.setLayoutY(125);
        root.getChildren().add(playButton);
        playButton.setOnAction(event -> {
            window.setScene(gameScene);
        });

        Button spectateButton = new Button("Spectate");
        spectateButton.setLayoutX(190);
        spectateButton.setLayoutY(170);
        root.getChildren().add(spectateButton);

        window.show();
    }
}
