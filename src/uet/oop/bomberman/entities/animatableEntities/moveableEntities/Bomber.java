package uet.oop.bomberman.entities.animatableEntities.moveableEntities;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovableEntities {
    private Scene scene = null;
    private int maxBombCount = 1;
    public static int flameSize = 1;

    public Bomber(int x, int y, Image img, Board board) {
        super(x,y,img, board);
        this.scene = board.getScene();
        inputHandler(scene);
        // Chỉnh hình chữ nhật cho khớp với nhân vật
        this.realBodyRectangle.setWidth(this.realBodyRectangle.getWidth() - 3 * Sprite.SCALE);
    }

    @Override
    public void update() {
        imageAnimationHandler();
        if(!alive) {
            if (deadAnimeTime > 0) {
                --deadAnimeTime;
            } else {
                BombermanGame.running = false;
            }
            return;
        }
        moveHandler();
        collisionHandler();
        printToScene("x: " + boardX + ", y: " + boardY + " " + facingDirection + ", Rectangle: " + realBodyRectangle.getX() + " " + realBodyRectangle.getY());
    }

    public void printToScene(String s) {
        Pane screenPane = (Pane) scene.getRoot();
        Label label = (Label) screenPane.getChildren().get(1);
        label.styleProperty().set("-fx-text-fill: blue;-fx-font-size: 16px;");
        //label.setText("x: " + boardX + ", y: " + boardY + " " + facingDirection + ", Rectangle: " + realBodyRectangle.getX() + " " + realBodyRectangle.getY());
        label.setText(s);
    }

    public void collide(Entity entity) {
        if (entity instanceof Balloon) {
            seftDestruct();
        }
    }

    // Hàm vẽ hình chữ nhật để debug
//    @Override
//    public void render(GraphicsContext gc) {
//        gc.fillRect(realBodyRectangle.getX(), realBodyRectangle.getY(),realBodyRectangle.getWidth(),realBodyRectangle.getHeight());
//    }

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
    public void imageAnimationHandler() {
        animate();

        if(!alive) {
            this.img = Sprite.movingSprite(Sprite.player_dead3, Sprite.player_dead2, Sprite.player_dead1, deadAnimeTime, 180).getFxImage();
            return;
        }

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
