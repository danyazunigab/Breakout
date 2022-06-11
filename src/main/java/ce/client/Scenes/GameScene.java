package ce.client.Scenes;

import ce.client.GameItems.Ball;
import ce.client.GameItems.Bar;
import ce.client.GameItems.Blocks.Block;
import ce.client.GameItems.PlayerBar;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class GameScene extends Scene{
    private final LinkedList<LinkedList<Block>> blocks = new LinkedList<>();
    private final LinkedList<Bar> bars = new LinkedList<>();
    private final LinkedList<Ball> balls = new LinkedList<>();
    private final Group group;
    private PlayerBar player;
    public GameScene(Group group,int[][] blockMatrix,int[] barList){
        super(group,800,750,Color.web("#112B3C"));
        this.group = group;
        this.drawBlocks(blockMatrix);
        //this.drawBars(barList);
        this.drawPlayer();
        this.drawBall(0);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT, A -> player.moveLeft();
                    case RIGHT, D -> player.moveRight();
                    default -> {}
                }
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT, A, RIGHT, D -> player.stop();
                    default -> {}
                }
            }
        });
    }
    private void drawBars(int[] barList){
        for (int i = 0; i < barList.length; i++) {
            Bar bar = new Bar(i,0,705);
            this.group.getChildren().add(bar.getRectangle());
            this.bars.add(bar);
        }
    }
    private void drawBlocks(int[][] blockMatrix){
        for (int j = 0; j < blockMatrix.length; j++) {
            LinkedList<Block> blockRow = new LinkedList<>();
            for (int i = 0; i < blockMatrix[0].length; i++) {
                Block block = new Block(new Integer[]{i, j},70*i+16,45*j+16);
                this.group.getChildren().add(block.getRectangle());
                blockRow.add(block);
            }
            this.blocks.add(blockRow);
        }
    }
    private void drawPlayer(){
        this.player = new PlayerBar(-1,(800/2)-50,705);
        this.group.getChildren().add(this.player.getRectangle());
        this.bars.add(this.player);
    }
    private void drawBall(int id){
        Ball ball = new Ball(id,(800/2)-10,645);
        this.group.getChildren().add(ball.getCircle());
        this.balls.add(ball);
    }
    public int getBallQuantity(){
        return this.balls.size();
    }

    public LinkedList<LinkedList<Block>> getBlocks() {
        return blocks;
    }

    public LinkedList<Bar> getBars() {
        return bars;
    }

    public LinkedList<Ball> getBalls() {
        return balls;
    }

    public PlayerBar getPlayer() {
        return player;
    }
}