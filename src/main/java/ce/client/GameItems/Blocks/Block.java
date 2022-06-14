package ce.client.GameItems.Blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block {
    private final Integer[] id;
    private final Rectangle rectangle;
    private int posX;
    private int posY;

    private Integer value = 0;

    public Block(Integer[] id, int posX, int posY) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.rectangle = new Rectangle(68, 43);
        this.rectangle.setX(posX);
        this.rectangle.setY(posY);
        this.changeRectangleFill(id[1]);
    }

    public void setColor(Color newColor) {
        this.rectangle.setFill(newColor);
    }

    public void changeRectangleFill(int level) { //Changes block color based on row
        if (level == 0 || level == 1) {
            this.setColor(Color.web("#e50000")); //Red
        } else if (level == 2 || level == 3) {
            this.setColor(Color.web("#ff5400")); //Orange
        } else if (level == 4 || level == 5) {
            this.setColor(Color.web("#ffb800")); //Yellow
        } else if (level == 6 || level == 7) {
            this.setColor(Color.web("#359900")); //Green
        }
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void setPosX(int newPosX) {
        this.posX = newPosX;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosY(int newPosY) {
        this.posY = newPosY;
    }

    public int getPosY() {
        return this.posY;
    }

    public Integer[] getID() {
        return this.id;
    }

    public void update(Integer value) {
        this.value = value;
        this.rectangle.setVisible(value != -1);
    }
}
