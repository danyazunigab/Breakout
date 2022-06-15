package ce.client.GameItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bar {
    private final Integer id;
    private final Rectangle rectangle;
    private Integer posX;
    private final Integer posY;
    public Bar(Integer id,Integer posX, Integer posY){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.rectangle = new Rectangle(100,20);
        this.rectangle.setX(posX);
        this.rectangle.setY(posY);
        this.rectangle.setFill(Color.web("#F66B0E"));
    }
    public Rectangle getRectangle() {
        return this.rectangle;
    }
    public Integer getId() {
        return id;
    }
    public Integer getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.rectangle.setX(posX);
        this.posX = posX;
    }
    public Integer getPosY() {
        return posY;
    }
    public void extend(){
        this.rectangle.setWidth(this.getRectangle().getWidth()*2);
    }
    public void shorten(){
        this.rectangle.setWidth(this.getRectangle().getWidth()*0.5);
    }

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
