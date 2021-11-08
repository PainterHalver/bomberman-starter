package uet.oop.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Sprite;


import static uet.oop.bomberman.utils.TerminalColor.*;

public class Bomber extends Entity implements Moveable, Animatable {
    public boolean up, left, down, right;
    public int anime = 0;
    public boolean moving;
    public String facingDirection = "";
    public Board board = null;
    private Scene scene = null;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }
    public Bomber(int x, int y, Image img, Board board) {
        super(x,y,img);
        this.board = board;
        this.scene = board.getScene();
        inputHandler(scene);
    }

    public void printLocationInCanvas() {
        log("x: " + x + ", y: " + y, ANSI_BLUE);
    }

    @Override
    public void update() {
        moveHandler();
        animate();
        animateImageHandler();
        //printLocationInCanvas();
    }

    @Override
    public void animate() {
        if(anime < 60) anime++;
        else anime = 0;
    }


    @Override
    public void moveHandler() {
        int xS = 0, yS = 0;
        if (up) yS -= 3;
        if (down) yS += 3;
        if (left) xS -= 3;
        if (right) xS += 3;
        //Thứ tự 4 cái này là quan trọng khi nhiều nút đươc bấm cùng lúc
        if (xS > 0) facingDirection = "RIGHT";
        if (xS < 0) facingDirection = "LEFT";
        if (yS > 0) facingDirection = "DOWN";
        if (yS < 0) facingDirection = "UP";

        if (xS == 0 && yS == 0) {
            moving = false;
            return;
        }

        move(xS,yS);
        moving = true;
    }

    @Override
    public void move(double xS, double yS) {
        this.x += xS;
        this.y += yS;
    }

    @Override
    public void canMove(int x, int y) {

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
