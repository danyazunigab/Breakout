package ce.client.GameItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Pseudo Singleton possible, not expected, but needed ?:D

/**
 * class that is used to read the Json received from Server
 */
public final class GameData {
    /**
     * a Jackson {@link com.fasterxml.jackson.databind.ObjectMapper} Mapper
     */
    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * Matrix of block states
     */
    public Integer[][] blocks;
    /**
     * a representation of the server struct Paddle
     */
    public paddle paddle;
    /**
     * An array of the representation of the server struct balls;
     */
    public ball[] balls;
    /**
     * [Debug]
     * a MSG from the server.
     * Not intented to see in the client
     */
    public String msg;

    /**
     * Update the GameData from the information in the String {@code json}
     *
     * @param json a String with json format that contains the data of the actual game state
     * @throws JsonProcessingException
     */
    public void update(String json) throws JsonProcessingException {
        Cdata data = mapper.readValue(json, Cdata.class);
        this.blocks = data.blocks;
        this.paddle = data.paddle;
        this.balls = data.balls;
        this.msg = data.msg;
    }

    /**
     * Inter Use class that represent the paddle struct of C Server.
     * Used in Jackson
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class paddle {
        @JsonProperty("left")
        Integer left;
        @JsonProperty("rigth")
        Integer rigth;
    }

    /**
     * Inter use class that represent the ball struct of C Server. Used in Jackson
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ball {
        @JsonProperty("x")
        Integer x;
        @JsonProperty("y")
        Integer y;
    }

    /**
     * Inter use class that represent the Game state given by the C Server. Used in Jackson.
     * Expected to use an intermediate between C and Java languages
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Cdata {
        @JsonProperty("blocks")
        Integer[][] blocks;
        @JsonProperty("paddle")
        paddle paddle;
        @JsonProperty("balls")
        ball[] balls;
        @JsonProperty("msg")
        String msg;
        @JsonProperty("life")
        Integer lives;
        @JsonProperty("points")
        Integer points;
    }

    public Integer[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer[][] blocks) {
        this.blocks = blocks;
    }

    public GameData.paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(GameData.paddle paddle) {
        this.paddle = paddle;
    }

    public GameData.ball[] getBalls() {
        return balls;
    }

    public void setBall(GameData.ball[] ball) {
        balls = ball;
    }

    /**
     * Getter
     *
     * @return Integer that is the left(X position) of the paddle(bar)
     */

    public Integer getPaddleLeft() {
        return paddle.left;
    }

    /**Getter
     *
     * @return Integer that is the actual size of the paddle(bar)
     */
    public Integer getPaddleSize() {
        return paddle.rigth - paddle.left;
    }

    /**Getter
     *
     * @return A Integer that is the amount of balls in game
     */
    public Integer getBallsLength() {
        return balls.length;
    }

    public static void main(String[] args) {
        String json = """
                        {"msg":  "","blocks":[[1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1]],
                        "paddle":       {
                                "left": 15,
                                "rigth":        15
                        },
                        "balls": [{
                                "x":    40,
                                "y":    40}]
                        
                }
                """;
        try {
            GameData data = new GameData();
            data.update(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}