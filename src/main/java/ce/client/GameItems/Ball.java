package ce.client.GameItems;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball implements GameItem{
    private Integer id;
    private final Circle circle;
    private Integer posX;
    private Integer posY;

    public Ball(Integer id,Integer posX,Integer posY){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.circle = new Circle(posX,posY,7);
        this.circle.setCenterX(posX);
        this.circle.setCenterY(posY);
        this.circle.setFill(Color.web("#EFEFEF"));
        this.circle.setStroke(Color.WHITESMOKE );
    }
    public Ball(){
        this.circle = new Circle();
        this.circle.setRadius(7);
        this.circle.setFill(Color.web("#EFEFEF"));
        this.circle.setStroke(Color.WHITESMOKE);
    }
    public Circle getShape(){
        return this.circle;
    }
    public void setPosX(Integer newPosX) {
        this.circle.setCenterX(newPosX);
        this.posX = newPosX;
    }
    public Integer getPosX(){
        return this.posX;
    }
    public void setPosY(Integer newPosY){
        this.circle.setCenterY(newPosY);
        this.posY = newPosY;
    }
    public Integer getPosY(){
        return this.posY;
    }
    public Integer getID(){
        return this.id;
    }
    public void setID(Integer newID){
        this.id = newID;
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
