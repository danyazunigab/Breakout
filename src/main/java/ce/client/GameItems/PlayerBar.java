package ce.client.GameItems;

/**
 * Moveable bar for the player
 */
public class PlayerBar extends Bar {

    /**
     * Current direction
     */
    private String direction="";

    /**
     * Test constructor
     * @param id
     * @param posX
     * @param posY
     */
    public PlayerBar(int id, int posX, int posY) {
        super(id, posX, posY);
    }

    /**
     * Factory constructor
     */
    public PlayerBar(){super();}

    /**
     * Changes direction to the left
     */
    public void moveLeft() {
        this.direction = "L";
    }

    /**
     * Changes direction to the right
     */
    public void moveRight() {
        this.direction = "R";

    }

    /**
     * Null direction (stops)
     */
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
