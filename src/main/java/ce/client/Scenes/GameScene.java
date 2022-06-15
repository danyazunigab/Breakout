package ce.client.Scenes;

import ce.client.GameItems.Ball;
import ce.client.GameItems.Bar;
import ce.client.GameItems.Blocks.Block;
import ce.client.GameItems.Factory.GameItemFactory;
import ce.client.GameItems.PlayerBar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.LinkedList;

public abstract class GameScene extends Scene {
    private Integer hp;
    private Integer pts;
    private final Label ptsLabel;
    private final Label hpLabel;
    protected final LinkedList<LinkedList<Block>> blocks = new LinkedList<>();
    protected final LinkedList<Bar> bars = new LinkedList<>();
    protected final LinkedList<Ball> balls = new LinkedList<>();
    protected final Group group;
    protected PlayerBar player;
    protected boolean exitFlag = false;
    protected GameItemFactory factory = new GameItemFactory();

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
        hpTitleLabel.setTextFill(Color.web("#EFEFEF"));
        hpTitleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.group.getChildren().add(hpTitleLabel);
        hpTitleLabel.setLayoutX(880);
        hpTitleLabel.setLayoutY(250);
        this.hpLabel = new Label();
        this.hpLabel.setTextFill(Color.web("#EFEFEF"));
        this.hpLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.group.getChildren().add(this.hpLabel);
        this.hpLabel.setLayoutX(900);
        this.hpLabel.setLayoutY(275);

        Label ptsTitleLabel = new Label("Points:");
        ptsTitleLabel.setTextFill(Color.web("#EFEFEF"));
        ptsTitleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.group.getChildren().add(ptsTitleLabel);
        ptsTitleLabel.setLayoutX(862);
        ptsTitleLabel.setLayoutY(400);
        this.ptsLabel = new Label();
        this.ptsLabel.setTextFill(Color.web("#EFEFEF"));
        this.ptsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.group.getChildren().add(this.ptsLabel);
        this.ptsLabel.setLayoutX(900);
        this.ptsLabel.setLayoutY(425);

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

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
        this.updateGameStatus();
    }

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
        this.updateGameStatus();
    }

    private void updateGameStatus(){
        this.hpLabel.setText(this.hp.toString());
        this.hpLabel.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                hpLabel.setLayoutX(900 - hpLabel.getWidth()/2);
            }
        });
        this.ptsLabel.setText(this.pts.toString());
        this.ptsLabel.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ptsLabel.setLayoutX(900 - ptsLabel.getWidth()/2);
            }
        });
    }
}