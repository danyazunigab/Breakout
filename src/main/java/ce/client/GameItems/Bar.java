package ce.client.GameItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bar {
    private final int id;
    private final Rectangle rectangle;
    private int posX;
    private final int posY;
    public Bar(int id,int posX, int posY){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.rectangle = new Rectangle(100,30);
        this.rectangle.setX(posX);
        this.rectangle.setY(posY);
        this.rectangle.setFill(Color.web("#F66B0E"));
    }
    public Rectangle getRectangle() {
        return this.rectangle;
    }
    public int getId() {
        return id;
    }
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.rectangle.setX(posX);
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void extend(){
        this.rectangle.setWidth(this.getRectangle().getWidth()*2);
    }
    public void shorten(){
        this.rectangle.setWidth(this.getRectangle().getWidth()*0.5);
    }

    public void setSize(Integer size){
        this.rectangle.setHeight(size);
    }
    public void update(GameData data){
        this.setPosX(data.getPaddleLeft());
        this.setSize(data.getPaddleSize());

    }
}
