package uet.oop.bomberman.entities.moveableEntities;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;


import static uet.oop.bomberman.utils.TerminalColor.*;

public class Bomber extends MovableEntities {
    private Scene scene = null;

    public Bomber(int x, int y, Image img, Board board) {
        super(x,y,img, board);
        this.scene = board.getScene();
        inputHandler(scene);
    }

    public void printLocationInCanvas() {
        Pane screenPane = (Pane) scene.getRoot();
        Label label = (Label) screenPane.getChildren().get(1);
        label.styleProperty().set("-fx-text-fill: blue;-fx-font-size: 20px;");
        label.setText("x: " + boardX + ", y: " + boardY + " " + facingDirection);
    }

    @Override
    public void update() {
        moveHandler();
        animate();
        animateImageHandler();
        printLocationInCanvas();
    }

    private void inputHandler(Scene scene) {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    this.up = true;
                    break;
                case RIGHT:
                    this.right = true;
                    break;
                case DOWN:
                    this.down = true;
                    break;
                case LEFT:
                    this.left = true;
                    break;
                default:
                    break;
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    this.up = false;
                    break;
                case RIGHT:
                    this.right = false;
                    break;
                case DOWN:
                    this.down = false;
                    break;
                case LEFT:
                    this.left = false;
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void animateImageHandler() {
        switch (facingDirection) {
            case "UP":
                this.img = Sprite.player_up.getFxImage();
                if (moving) {
                    this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, anime, 20).getFxImage();
                }
                break;
            case "DOWN":
                this.img = Sprite.player_down.getFxImage();
                if (moving) {
                    this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, anime, 20).getFxImage();
                }
                break;
            case "LEFT":
                this.img = Sprite.player_left.getFxImage();
                if (moving) {
                    this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, anime, 20).getFxImage();
                }
                break;
            default:
                this.img = Sprite.player_right.getFxImage();
                if (moving) {
                    this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, anime, 20).getFxImage();
                }
        }
    }
}
