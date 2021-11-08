package uet.oop.bomberman.entities.moveableEntities;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Animatable;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;


import static uet.oop.bomberman.utils.TerminalColor.*;

public class Bomber extends MoveableEntities implements Animatable {
    public boolean up, left, down, right;
    public int anime = 0;
    public boolean moving;
    public String facingDirection = "";
    public Board board = null;
    private Scene scene = null;

    public Bomber(int boardX, int boardY, Image img) {
        super( boardX, boardY, img);
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
    public void move(int xS, int yS) {
        if (canMove(xS, 0)) this.x += xS;
        if (canMove(0, yS))this.y += yS;
        //Cập nhật boardX,Y
        boardX = x / Sprite.SCALED_SIZE;
        boardY = y / Sprite.SCALED_SIZE;
    }

    @Override
    public boolean canMove(int xS, int yS) {
        Stage stage = (Stage) this.scene.getWindow();
        stage.setTitle(boardX + " " + boardY);

        int topLeftX = x + xS;
        int topLeftY = y + yS + 5 * Sprite.SCALE; // cúi cái đầu xuống 1 chút :)
        int topRightX = topLeftX + (Sprite.player_down.getRealWidth() - 1) * Sprite.SCALE;
        int topRightY = topLeftY;
        int botLeftX = topLeftX;
        int botLeftY = topLeftY + (Sprite.player_down.getRealHeight() - 5) * Sprite.SCALE;
        int botRightX = topRightX;
        int botRightY = botLeftY;


        Entity object = null;

        object = board.getStillObjectByCanvas(topLeftX, topLeftY);
        if((object instanceof Wall || object instanceof Brick)) return false;

        object = board.getStillObjectByCanvas(topRightX, topRightY);
        if((object instanceof Wall || object instanceof Brick)) return false;

        object = board.getStillObjectByCanvas(botLeftX, botLeftY);
        if((object instanceof Wall || object instanceof Brick)) return false;

        object = board.getStillObjectByCanvas(botRightX, botRightY);
        if((object instanceof Wall || object instanceof Brick)) return false;

        return true;
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
