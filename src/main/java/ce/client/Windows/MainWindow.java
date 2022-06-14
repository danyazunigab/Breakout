package ce.client.Windows;

import ce.client.GameItems.GameData;
import ce.client.Scenes.GameScene;
import ce.client.Sockets.Socket;
import ce.client.Sockets.SocketFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainWindow {

    private static GameScene gameScene;

    private static final GameData data = new GameData();

    private static Socket socket;

    public static void launch(Stage window) {

        window.setTitle("Breakout");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 350, Color.web("#112B3C"));
        window.setScene(scene);

        Group game = new Group();
        int[][] testBlockMatrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[] testBarList = new int[]{1, 2, 3};
        gameScene = new GameScene(game, testBlockMatrix, testBarList);
        Button playButton = new Button("Play");
        playButton.setLayoutX(190);
        playButton.setLayoutY(125);
        root.getChildren().add(playButton);
        playButton.setOnAction(event -> {
            window.setScene(gameScene);
            new Thread(MainWindow::initClient).start();
        });

        Button spectateButton = new Button("Spectate");
        spectateButton.setLayoutX(190);
        spectateButton.setLayoutY(170);
        root.getChildren().add(spectateButton);

        window.show();
    }

    /**
     * Update the Scene elements with the {@code Gamedata} information
     */
    public static void updateScene() {

        for (Integer i = 0; i < data.blocks.length ; i++) {
            for (Integer j = 0; j < data.blocks[i].length ; j++) {
                gameScene.getBlocks().get(i).get(j).update(data.blocks[i][j]);
            }
        }
        gameScene.getPlayer().update(data);
        for (Integer i = 0; i < data.getBallsLength() ; i++) {
            gameScene.getBalls().get(i).update(data.getBalls()[i]);
        }
    }

    /**
     * Init the client as a Client for the server and start to  update the Scene elements with the information
     * recieved from server.
     * Init the Socket as a client Socket
     */
    public static void initClient() {

        try {
            socket = SocketFactory.clientSocket(0);
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                String json = socket.recieve();
                data.update(json);
                updateScene();
                if (socket.send(gameScene.getPlayer().getDirection()) == -1) {
                    System.out.println("Error sending msg to server");
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
