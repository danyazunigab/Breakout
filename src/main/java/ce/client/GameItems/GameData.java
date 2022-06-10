package ce.client.GameItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

//Pseudo Singleton not expected, but needed ¿?:D
/**
 * Abstract class that is used to read the jackson received from Server
 * */
public final class GameData {

    private static final ObjectMapper mapper=new ObjectMapper();

    public Integer[][] blocks;
    public  paddle paddle;
    public  ball[] balls;

    public  String msg;


    public void update(String json) throws JsonProcessingException {
        Cdata data =mapper.readValue(json, Cdata.class);
        this.blocks=data.blocks;
        this.paddle= data.paddle;
        this.balls=data.balls;
        this.msg=data.msg;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class paddle {
        @JsonProperty("left")
        Integer left;
        @JsonProperty("rigth")
        Integer rigth;
    };
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ball {
        @JsonProperty("x")
        Integer x;
        @JsonProperty("y")
        Integer y;
    };

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


    public Integer getPaddleLeft(){
        return paddle.left;
    }
    public Integer getPaddleSize(){
        return paddle.rigth-paddle.left;
    }
    public Integer getBallsLength(){
        return balls.length;
    }

    public static void main(String[] args) {
        String json= """
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
            GameData data=new GameData() ;
            data.update(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}