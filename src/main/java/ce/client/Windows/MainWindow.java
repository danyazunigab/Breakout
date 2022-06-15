package ce.client.Windows;

import ce.client.GameItems.GameData;
import ce.client.Scenes.GameScene;
import ce.client.Scenes.PlayScene;
import ce.client.Scenes.SpectateScene;
import ce.client.Sockets.Socket;
import ce.client.Sockets.SocketFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainWindow {

    private static GameScene showingScene;

    private static final GameData data = new GameData();

    private static Socket socket;
    private static final String buttonStyle = """
                {
                -fx-padding: 8 15 15 15;
                -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;
                -fx-background-radius: 8;
                -fx-background-color:\s
                    linear-gradient(from 0% 93% to 0% 100%, #db5800 0%, #F66B0E 100%),
                    #F66B0E,
                    #F66B0E;
                -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );
                -fx-font-weight: bold;
                -fx-font-size: 1.1em;
            }
            """;

    public static void launch(Stage window) {

        window.setTitle("Breakout");
        Group root = new Group();
        Scene scene = new Scene(root, 450, 300, Color.web("#112B3C"));
        window.setScene(scene);

        Group game = new Group();
        Integer[][] testBlockMatrix = new Integer[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        Integer[] testBarList = new Integer[]{1, 2, 3};

        Button playButton = new Button("Play");
        root.getChildren().add(playButton);

        Button spectateButton = new Button("Spectate");
        root.getChildren().add(spectateButton);

        window.show();

        playButton.setLayoutX(225 - playButton.getWidth() / 2);
        playButton.setLayoutY(180);
        playButton.setStyle(buttonStyle);
        playButton.setOnAction(event -> {
            showingScene = new PlayScene(game, testBlockMatrix, testBarList);
            window.setScene(showingScene);
            new Thread(MainWindow::initPlayerSocket).start();
        });

        spectateButton.setLayoutX(225 - spectateButton.getWidth() / 2);
        spectateButton.setLayoutY(220);
        spectateButton.setStyle(buttonStyle);
        spectateButton.setOnAction(event -> {
            showingScene = new SpectateScene(game, testBlockMatrix, testBarList);
            window.setScene(showingScene);
            new Thread(MainWindow::initViewerSocket).start();
        });

        try {
            FileInputStream inputStream = new FileInputStream("src/img/Title.png");
            Image title = new Image(inputStream);
            ImageView titleHolder = new ImageView(title);
            root.getChildren().add(titleHolder);
            titleHolder.setX(25);
            titleHolder.setY(50);
        } catch (FileNotFoundException e) {
            System.out.println("No encontro el titulo.");
        } catch (SecurityException e) {
            System.out.println("Error de seguridad");
        }
    }

    /**
     * Update the Scene elements with the {@code Gamedata} information
     */
    public static void updateScene() {

        for (Integer i = 0; i < data.blocks.length; i++) {
            for (Integer j = 0; j < data.blocks[i].length; j++) {
                showingScene.getBlocks().get(i).get(j).update(data.blocks[i][j]);
            }
        }
        showingScene.getPlayer().update(data);
        for (Integer i = 0; i < data.getBallsLength(); i++) {
            showingScene.getBalls().get(i).update(data.getBalls()[i]);
        }
    }

    /**
     * Init the client as a Client for the server and start to  update the Scene elements with the information
     * recieved from server.
     * Init the Socket as a client Socket
     */
    public static void initPlayerSocket() {
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
                if (socket.send(showingScene.getPlayer().getDirection()) == -1) {
                    System.out.println("Error sending msg to server");
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void initViewerSocket() {
        try {
            socket = SocketFactory.viewerSocket(0);
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                String json = socket.recieve();
                data.update(json);
                updateScene();
                if (showingScene.getExitFlag()) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
