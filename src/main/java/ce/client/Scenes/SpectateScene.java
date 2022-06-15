package ce.client.Scenes;

import ce.client.GameItems.Ball;
import ce.client.GameItems.Bar;
import ce.client.GameItems.Blocks.Block;
import ce.client.GameItems.PlayerBar;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

import java.util.LinkedList;

public class SpectateScene extends GameScene {
    public SpectateScene(Group group, Integer[][] blockMatrix, Integer[] barList) {
        super(group, blockMatrix, barList);
        this.configureKeyBindings();
    }

    @Override
    protected void configureKeyBindings() {
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESCAPE -> {
                        System.out.println("Agregar opcion de salir");
                        exit();
                    }
                    default -> {
                    }
                }
            }
        });
    }
}
