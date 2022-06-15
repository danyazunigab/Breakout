package ce.client.GameItems;

import javafx.scene.shape.Shape;

/**
 * Interface Game Item
 */
public interface GameItem {
    public Shape getShape(); //Shape can be any JavaFX shape
    public Integer getPosX();
    public Integer getPosY();
    public void setPosX(Integer newPosX);
}
