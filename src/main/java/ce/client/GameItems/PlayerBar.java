package ce.client.GameItems;

public class PlayerBar extends Bar {
    private final int speed = 15;

    private String direction="";

    public PlayerBar(int id, int posX, int posY) {
        super(id, posX, posY);
    }

    public void moveLeft() {
        this.direction = "L";

    }

    public void moveRight() {
        this.direction = "R";

    }

    public void stop() {
        this.direction = "";
    }

    /**Getter
     *
     * @return A String with only a char, 'L' for left and 'R' for right
-     */
    public String getDirection() {
        return direction;
    }
}
