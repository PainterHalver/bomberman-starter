package uet.oop.bomberman.entities.animatableEntities.moveableEntities;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.animatableEntities.Bomb;
import uet.oop.bomberman.entities.animatableEntities.Flame;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.Balloon;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovableEntities {
    private Scene scene = null;
    private int maxBomb = 1;
    public static int flameSize = 1;

    public Bomber(int x, int y, Image img, Board board) {
        super(x,y,img, board);
        this.scene = board.getScene();
        inputHandler(scene);
        // Chỉnh hình chữ nhật cho khớp với nhân vật
        this.realBodyRectangle.setWidth(this.realBodyRectangle.getWidth() - 6 * Sprite.SCALE);
        this.realBodyRectangle.setHeight(this.realBodyRectangle.getHeight() - 4 * Sprite.SCALE);
        this.realBodyRectangle.setY(this.realBodyRectangle.getY() + 2 * Sprite.SCALE);
        this.realBodyRectangle.setX(this.realBodyRectangle.getX() + 1 * Sprite.SCALE);
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
        printToScene("Entities: " + board.getEntities().size() + " StillObjects: " + board.getStillObjects().size());
    }

    public void plantBomb() {
        if (!(board.bombCount() < maxBomb)) return;

        // Đặt bomb ở tâm của Bomber
        int boardPositionX = (int) (x + realBodyRectangle.getWidth() / 2) / Sprite.SCALED_SIZE;
        int boardPositionY = (int) (y + realBodyRectangle.getHeight() / 2) / Sprite.SCALED_SIZE;
        board.addStillObject(new Bomb(boardPositionX, boardPositionY, Sprite.bomb.getFxImage(), board));
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
        if (entity instanceof BombItem) {
            this.maxBomb++;
            entity.removeFromBoard();
        }
        if (entity instanceof FlameItem) {
            Bomber.flameSize++;
            entity.removeFromBoard();
        }
        if (entity instanceof SpeedItem) {
            this.speed+= 0.5;
            entity.removeFromBoard();
        }
        if (entity instanceof Portal) {
            if (((Portal) entity).isOpened()) {
                // TODO: Xử lý qua màn
                seftDestruct();
            }
        }
    }

    // Hàm vẽ hình chữ nhật để debug
//    @Override
//    public void render(GraphicsContext gc) {
//        gc.drawImage(img, x, y);
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
                case SPACE:
                    plantBomb();
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
            this.img = Sprite.oneCycleMovingSprite(Sprite.player_dead3, Sprite.player_dead2, Sprite.player_dead1, deadAnimeTime, 40).getFxImage();
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
