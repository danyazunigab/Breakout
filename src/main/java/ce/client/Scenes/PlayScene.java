package ce.client.Scenes;

import ce.client.GameItems.Ball;
import ce.client.GameItems.Bar;
import ce.client.GameItems.Blocks.Block;
import ce.client.GameItems.PlayerBar;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

import java.util.LinkedList;

public class PlayScene extends GameScene{
    public PlayScene(Group group, Integer[][] blockMatrix, Integer[] barList) {
        super(group, blockMatrix, barList);
        this.drawPlayer();
        this.configureKeyBindings();
    }

    protected void configureKeyBindings(){
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT, A -> player.moveLeft();
                    case RIGHT, D -> player.moveRight();
                    case ESCAPE -> {
                        System.out.println("Agregar salir");
                        exit();
                    }
                    default -> {}
                }
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT, A, RIGHT, D -> player.stop();
                    default -> {}
                }
            }
        });
    }
}