package ce.client.GameItems;

public class PlayerBar extends Bar{
    private int speed = 15;
    public PlayerBar(int id, int posX, int posY) {
        super(id, posX, posY);
    }
    public void moveLeft(){
        this.setPosX(this.getPosX()-this.speed);
    }
    public void moveRight(){
        this.setPosX(this.getPosX()+this.speed);
    }
}
