package ce.client.GameItems.Factory;

import ce.client.GameItems.Ball;
import ce.client.GameItems.Bar;
import ce.client.GameItems.Blocks.Block;
import ce.client.GameItems.GameItem;
import ce.client.GameItems.PlayerBar;

/**
 * Creates Game Items' instances
 * Ball,Bar,PlayerBar and Blocks
 */
public class GameItemFactory{
    public GameItem create(String type) {
        switch (type){
            case "ball" -> {
                return new Ball();
            }
            case "bar" -> {
                return new Bar();
            }
            case "player" -> {
                return new PlayerBar();
            }
            case "block" -> {
                return new Block();
            }
            default -> {return null;}
        }
    }
}
