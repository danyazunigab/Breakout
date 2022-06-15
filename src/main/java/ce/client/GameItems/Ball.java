package ce.client.GameItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    private final Integer id;
    private final Circle circle;
    private Integer posX;
    private Integer posY;

    public Ball(int id,int posX,int posY){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.circle = new Circle(posX,posY,7);
        this.circle.setCenterX(posX);
        this.circle.setCenterY(posY);
        this.circle.setFill(Color.web("#EFEFEF"));
        this.circle.setStroke(Color.WHITESMOKE );
    }
    public Circle getCircle(){
        return this.circle;
    }
    public void setPosX(int newPosX) {
        this.circle.setCenterX(newPosX);
        this.posX = newPosX;
    }
    public Integer getPosX(){
        return this.posX;
    }
    public void setPosY(int newPosY){
        this.circle.setCenterY(newPosY);
        this.posY = newPosY;
    }
    public Integer getPosY(){
        return this.posY;
    }
    public Integer getID(){
        return this.id;
    }

    /**Update the state of the ball
     *
     * @param ball a {@link ce.client.GameItems.GameData.ball} item
     */
    public void update(GameData.ball ball){
        setPosX(ball.x);
        setPosY(ball.y);
    }
}
