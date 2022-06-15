package ce.client.GameItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Bar class
 */
public class Bar implements GameItem{
    /**
     * Bar id
     */
    private Integer id;
    /**
     * Bar shape
     */
    private final Rectangle rectangle;
    /**
     * Bar X position
     */
    private Integer posX;
    /**
     * Bar Y position
     */
    private final Integer posY;

    /**
     * Bar test constructor
     * @param id
     * @param posX
     * @param posY
     */
    public Bar(Integer id,Integer posX, Integer posY){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.rectangle = new Rectangle(100,20);
        this.rectangle.setX(posX);
        this.rectangle.setY(posY);
        this.rectangle.setFill(Color.web("#F66B0E"));
    }

    /**
     * Factory constructor
     */
    public Bar(){
        this.posY = 705;
        this.rectangle = new Rectangle(100,20);
        this.rectangle.setY(705);
        this.rectangle.setFill(Color.web("#F66B0E"));
    }

    /**
     * Returns Bar shape
     * @return rectangle
     */
    public Rectangle getShape() {
        return this.rectangle;
    }

    /**
     * Returns bar ID
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets new Bar ID
     * @param newID ID
     */
    public void setID(Integer newID){
        this.id = newID;
    }

    /**
     * Gets Bar X position
     * @return
     */
    public Integer getPosX() {
        return posX;
    }

    /**
     * Sets new bar X position
     * @param posX
     */
    public void setPosX(Integer posX) {
        this.rectangle.setX(posX);
        this.posX = posX;
    }

    /**
     * Returns bar Y position
     * @return
     */
    public Integer getPosY() {
        return posY;
    }

    /**
     * Sets bar size
     * @param size new size
     */
    public void setSize(Integer size){
        this.rectangle.setWidth(size);
    }

    /**Update the state of the Bar
     *
     * @param data a {@link ce.client.GameItems.GameData} instance that contains the state of the bar as a {@link  ce.client.GameItems.GameData.paddle} object
     */
    public void update(GameData data){
        this.setPosX(data.getPaddleLeft());
        this.setSize(data.getPaddleSize());

    }
}
