package ce.client.GameItems;

import javafx.scene.shape.Shape;

public interface GameItem {
    public Shape getShape();
    public Integer getPosX();
    public Integer getPosY();
    public void setPosX(Integer newPosX);
}
