package ce.client.GameItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    private final int id;
    private final Circle circle;
    private int posX;
    private int posY;

    public Ball(int id,int posX,int posY){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.circle = new Circle(posX,posY,20);
        this.circle.setLayoutX(posX);
        this.circle.setLayoutY(posY);
        this.circle.setFill(Color.web("#EFEFEF"));
    }
    public Circle getCircle(){
        return this.circle;
    }
    public void setPosX(int newPosX) {
        this.circle.setLayoutX(newPosX);
        this.posX = newPosX;
    }
    public int getPosX(){
        return this.posX;
    }
    public void setPosY(int newPosY){
        this.circle.setLayoutY(newPosY);
        this.posY = newPosY;
    }
    public int getPosY(){
        return this.posY;
    }
    public int getID(){
        return this.id;
    }

    public void update(GameData.ball ball){
        setPosX(ball.x);
        setPosY(ball.y);
    }
}
