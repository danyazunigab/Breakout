package ce.client.GameItems.Blocks;

import ce.client.GameItems.GameItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Block class
 */
public class Block implements GameItem {
    private Integer[] id;
    private final Rectangle rectangle;
    private Integer posX;
    private Integer posY;

    private Integer value = 0;

    /**
     * Test constructor
     * @param id
     * @param posX
     * @param posY
     */
    public Block(Integer[] id, Integer posX, Integer posY) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.rectangle = new Rectangle(68, 43);
        this.rectangle.setX(posX);
        this.rectangle.setY(posY);
        this.changeRectangleFill(id[1]);
    }

    /**
     * Factory constructor
     */
    public Block(){
        this.rectangle = new Rectangle(68,43);
    }

    /**
     * Sets rectangle color
     * @param newColor
     */
    private void setColor(Color newColor) {
        this.rectangle.setFill(newColor);
    }

    /**
     * Changes rectangle color based on its row in the block matrix
     * @param level
     */
    public void changeRectangleFill(Integer level) { //Changes block color based on row
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

    /**
     * Shape Getter
     * @return rectangle
     */
    public Rectangle getShape() {
        return this.rectangle;
    }

    /**
     * Sets new rectangle X position
     * @param newPosX
     */
    public void setPosX(Integer newPosX) {
        this.posX = newPosX;
        this.rectangle.setX(this.posX);
    }

    /**
     * Pos X getter
     * @return posX
     */
    public Integer getPosX() {
        return this.posX;
    }

    /**
     * Sets new rectangle Y position
     * @param newPosY
     */
    public void setPosY(Integer newPosY) {
        this.posY = newPosY;
        this.rectangle.setY(this.posY);
    }

    /**
     * Pos Y getter
     * @return posY
     */
    public Integer getPosY() {
        return this.posY;
    }

    /**
     * ID getter
     * @return ID
     */
    public Integer[] getID() {
        return this.id;
    }

    /**
     * Sets new ID to block
     * @param newID
     */
    public void setID(Integer[] newID){
        this.id = newID;
        this.changeRectangleFill(newID[1]);
    }

    /**
     * Updates the rectangle state (value)
     * @param value
     */
    public void update(Integer value) {
        this.value = value;
        this.rectangle.setVisible(value != -1);
    }
}
