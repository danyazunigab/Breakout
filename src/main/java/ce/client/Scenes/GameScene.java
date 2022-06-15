package ce.client.Scenes;

import ce.client.GameItems.Ball;
import ce.client.GameItems.Bar;
import ce.client.GameItems.Blocks.Block;
import ce.client.GameItems.Factory.GameItemFactory;
import ce.client.GameItems.PlayerBar;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public abstract class GameScene extends Scene {
    protected final LinkedList<LinkedList<Block>> blocks = new LinkedList<>();
    protected final LinkedList<Bar> bars = new LinkedList<>();
    protected final LinkedList<Ball> balls = new LinkedList<>();
    protected final Group group;
    protected PlayerBar player;
    protected boolean exitFlag = false;
    protected GameItemFactory factory = new GameItemFactory();

    public GameScene(Group group, Integer[][] blockMatrix, Integer[] barList) {
        super(group, 800, 750, Color.web("#112B3C"));
        this.group = group;
        this.drawBlocks(blockMatrix);
        this.drawPlayer();
        for (Integer i = 0; i < 3; i++) {
            this.addBall();
        }

    }
    protected void drawBars(Integer[] barList){
        for (int i = 0; i < barList.length; i++) {
            Bar bar = (Bar) factory.create("bar");
            this.group.getChildren().add(bar.getShape());
            this.bars.add(bar);
        }
    }

    protected void drawBlocks(Integer[][] blockMatrix) {
        for (Integer j = 0; j < blockMatrix.length; j++) {
            LinkedList<Block> blockRow = new LinkedList<>();
            for (Integer i = 0; i < blockMatrix[0].length; i++) {
                Block block = (Block) factory.create("block");
                block.setID(new Integer[]{i,j});
                block.setPosX(70 * i + 16);
                block.setPosY(45 * j + 16);
                this.group.getChildren().add(block.getShape());
                blockRow.add(block);
            }
            this.blocks.add(blockRow);
        }
    }
//this.balls.size(),400,745
    protected Ball drawSingularBall() {
        Ball ball = (Ball) factory.create("ball");
        ball.setID(this.balls.size());
        ball.setPosX(400);
        ball.setPosY(745);
        return ball;
    }
    protected void drawBalls(){
        if(this.balls.isEmpty()){
            this.addBall();
        }else{
            for (Ball ball: this.balls) {
                this.group.getChildren().add(ball.getShape());
            }
        }
    }

    public void addBall() {
        Ball newBall = drawSingularBall();
        this.balls.add(newBall);
        this.group.getChildren().add(newBall.getShape());
    }

    public Integer getBallQuantity() {
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

    protected abstract void configureKeyBindings();

    protected void drawPlayer(){
        //this.player = new PlayerBar(-1,(800/2)-50,705);
        this.player = (PlayerBar) factory.create("player");
        this.player.setID(-1);
        this.player.setPosX((800/2)-50);
        this.group.getChildren().add(this.player.getShape());
        this.bars.add(this.player);
    }

    public PlayerBar getPlayer() {
        return this.player;
    }

    public boolean getExitFlag() {
        return this.exitFlag;
    }

    protected void exit() {
        this.exitFlag = true;
    }
}