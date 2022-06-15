package ce.client.Scenes;

import ce.client.GameItems.Ball;
import ce.client.GameItems.Bar;
import ce.client.GameItems.Blocks.Block;
import ce.client.GameItems.Factory.GameItemFactory;
import ce.client.GameItems.PlayerBar;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.LinkedList;

/**
 * General game scene
 */
public abstract class GameScene extends Scene {
    /**
     * Player points
     */
    private Integer pts = 0;
    /**
     * Showing points
     */
    private String ptsStr = "0";
    /**
     * Points label
     */
    private final Label ptsLabel = new Label(this.ptsStr);
    /**
     * Player health points (lives)
     */
    private Integer hp = 0;
    /**
     * Showing HP
     */
    private String hpStr = "0";
    /**
     * Health label
     */
    private final Label hpLabel = new Label(this.hpStr);
    /**
     * Block matrix
     */
    protected final LinkedList<LinkedList<Block>> blocks = new LinkedList<>();
    /**
     * Bar list
     */
    protected final LinkedList<Bar> bars = new LinkedList<>();
    /**
     * Ball list
     */
    protected final LinkedList<Ball> balls = new LinkedList<>();
    /**
     * Game group
     */
    protected final Group group;
    /**
     * Player bar
     */
    protected PlayerBar player;
    /**
     * Flag to exit game
     */
    protected boolean exitFlag = false;
    /**
     * Game Item factory
     */
    protected GameItemFactory factory = new GameItemFactory();

    /**
     * Game Scene constructor
     * @param group group
     * @param blockMatrix block matrix
     * @param barList bar list
     */
    public GameScene(Group group, Integer[][] blockMatrix, Integer[] barList) {
        super(group, 1000, 750, Color.web("#112B3C"));
        this.group = group;

        Rectangle statBg = new Rectangle(200,750);
        statBg.setFill(Color.web("#112B3C"));
        statBg.setStroke(Color.web("#F66B0E"));
        statBg.setStrokeType(StrokeType.INSIDE);
        statBg.setStrokeWidth(3);
        statBg.setX(800);
        statBg.setY(0);
        this.group.getChildren().add(statBg);

        Label hpTitleLabel = new Label("HP:");
        this.group.getChildren().add(hpTitleLabel);
        hpTitleLabel.setTextFill(Color.web("#EFEFEF"));
        hpTitleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        hpTitleLabel.setLayoutX(880);
        hpTitleLabel.setLayoutY(250);
        this.group.getChildren().add(this.hpLabel);
        this.hpLabel.setTextFill(Color.web("#EFEFEF"));
        this.hpLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.hpLabel.setLayoutX(900);
        this.hpLabel.setLayoutY(275);

        Label ptsTitleLabel = new Label("Points:");
        this.group.getChildren().add(ptsTitleLabel);
        ptsTitleLabel.setTextFill(Color.web("#EFEFEF"));
        ptsTitleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        ptsTitleLabel.setLayoutX(862);
        ptsTitleLabel.setLayoutY(400);
        this.group.getChildren().add(this.ptsLabel);
        this.ptsLabel.setTextFill(Color.web("#EFEFEF"));
        this.ptsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        this.ptsLabel.setLayoutX(900);
        this.ptsLabel.setLayoutY(425);

        this.drawBlocks(blockMatrix);
        this.drawPlayer();
        for (Integer i = 0; i < 3; i++) {
            this.addBall();
        }
    }

    /**
     * Draws the bar list on screen
     * @param barList bar list
     */
    protected void drawBars(Integer[] barList){
        for (int i = 0; i < barList.length; i++) {
            Bar bar = (Bar) factory.create("bar");
            this.group.getChildren().add(bar.getShape());
            this.bars.add(bar);
        }
    }

    /**
     * Draws the block matrix on screen
     * @param blockMatrix block matrix
     */
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

    /**
     * Draws a ball
     * @return ball
     */
    protected Ball drawSingularBall() {
        Ball ball = (Ball) factory.create("ball");
        ball.setID(this.balls.size());
        ball.setPosX(400);
        ball.setPosY(900);
        return ball;
    }

    /**
     * Draws the ball list on screen
     */
    protected void drawBalls(){
        if(this.balls.isEmpty()){
            this.addBall();
        }else{
            for (Ball ball: this.balls) {
                this.group.getChildren().add(ball.getShape());
            }
        }
    }

    /**
     * Adds one ball to the screen
     */
    public void addBall() {
        Ball newBall = drawSingularBall();
        this.balls.add(newBall);
        this.group.getChildren().add(newBall.getShape());
    }

    /**
     * Returns ball quantity
     * @return ball quantity
     */
    public Integer getBallQuantity() {
        return this.balls.size();
    }

    /**
     * Returns block matrix
     * @return block matrix
     */
    public LinkedList<LinkedList<Block>> getBlocks() {
        return blocks;
    }

    /**
     * Returns bar list
     * @return bars
     */
    public LinkedList<Bar> getBars() {
        return bars;
    }

    /**
     * Returns ball list
     * @return balls
     */
    public LinkedList<Ball> getBalls() {
        return balls;
    }

    /**
     * Player's actions supported (depends on type of client)
     */
    protected abstract void configureKeyBindings();

    /**
     * Draws the player bar on screen
     */
    protected void drawPlayer(){
        this.player = (PlayerBar) factory.create("player");
        this.player.setID(-1);
        this.player.setPosX((800/2)-50);
        this.group.getChildren().add(this.player.getShape());
        this.bars.add(this.player);
    }

    /**
     * Returns the player bar
     * @return player bar
     */
    public PlayerBar getPlayer() {
        return this.player;
    }

    /**
     * Gets exit flag
     * @return exit flag
     */
    public boolean getExitFlag() {
        return this.exitFlag;
    }

    /**
     * Exits
     */
    protected void exit() {
        this.exitFlag = true;
    }

    /**
     * Returns current HP
     * @return HP
     */
    public Integer getHp() {
        return hp;
    }

    /**
     * Sets new HP
     * @param hp players' health points
     */
    public void setHp(Integer hp) {
        this.hp = hp;
        this.hpStr = this.hp.toString();
        this.updateLabels();
    }

    /**
     * returns current points
     * @return points
     */
    public Integer getPts() {
        return pts;
    }

    /**
     * Sets new points value
     * @param pts points
     */
    public void setPts(Integer pts) {
        this.pts = pts;
        this.ptsStr = this.pts.toString();
        this.updateLabels();
    }

    /**
     * Updates the labels for HP and points
     */
    private void updateLabels(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ptsLabel.setText(ptsStr);
                ptsLabel.setLayoutX(900-ptsLabel.getWidth()/2);
                hpLabel.setText(hpStr);
                hpLabel.setLayoutX(900-hpLabel.getWidth()/2);
            }
        });

    }

    /**
     * Updates the game status variables (HP and points)
     * @param lives player HP
     * @param points player points
     */
    public void updateGameStatus(Integer lives, Integer points){
        this.setHp(lives);
        this.setPts(points);
    }
}