package ce.client.Scenes;

import ce.client.GameItems.Ball;
import ce.client.GameItems.Bar;
import ce.client.GameItems.Blocks.Block;
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

    public GameScene(Group group, Integer[][] blockMatrix, Integer[] barList) {
        super(group, 800, 750, Color.web("#112B3C"));
        this.group = group;
        this.drawBlocks(blockMatrix);
        for (Integer i = 0; i < 3; i++) {
            this.addBall();
        }

    }

    protected void drawBars(Integer[] barList) {
        for (Integer i = 0; i < barList.length; i++) {
            Bar bar = new Bar(i, 0, 705);
            this.group.getChildren().add(bar.getRectangle());
            this.bars.add(bar);
        }
    }

    protected void drawBlocks(Integer[][] blockMatrix) {
        for (Integer j = 0; j < blockMatrix.length; j++) {
            LinkedList<Block> blockRow = new LinkedList<>();
            for (Integer i = 0; i < blockMatrix[0].length; i++) {
                Block block = new Block(new Integer[]{i, j}, 70 * i + 16, 45 * j + 16);
                this.group.getChildren().add(block.getRectangle());
                blockRow.add(block);
            }
            this.blocks.add(blockRow);
        }
    }

    protected Ball drawSingularBall() {
        return new Ball(this.balls.size(), 400, 745);
    }

    protected void drawBalls() {
        if (this.balls.isEmpty()) {
            this.addBall();
        } else {
            for (Ball ball : this.balls) {
                this.group.getChildren().add(ball.getCircle());
            }
        }
    }

    public void addBall() {
        Ball newBall = drawSingularBall();
        this.balls.add(newBall);
        this.group.getChildren().add(newBall.getCircle());
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

    protected void drawPlayer() {
        this.player = new PlayerBar(-1, (800 / 2) - 50, 705);
        this.group.getChildren().add(this.player.getRectangle());
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