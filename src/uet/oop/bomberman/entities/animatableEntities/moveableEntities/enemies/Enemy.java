package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.Bomb;
import uet.oop.bomberman.entities.animatableEntities.Brick;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.MovableEntities;
import uet.oop.bomberman.entities.stillEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends MovableEntities {
  //Vì Sprite của enemy chỉ có 2 hướng trái phải nên cần 1 biến lưu hướng trái hay phải đang hướng tới
  protected String lastHorizontalDirection = "LEFT"; // LEFT, RIGHT
  protected final double dieAnimationTime = 0.85 * 2 * 60;
  protected int deadAnime = 0;

  public Enemy(int x, int y, Image img, Board board) {
    super(x,y,img, board);

    this.realBodyRectangle.setWidth(this.realBodyRectangle.getWidth() - 2 * Sprite.SCALE);
    this.realBodyRectangle.setHeight(this.realBodyRectangle.getHeight() - 2 * Sprite.SCALE);
    this.realBodyRectangle.setY(this.realBodyRectangle.getY() + 1 * Sprite.SCALE);
    this.realBodyRectangle.setX(this.realBodyRectangle.getX() + 1 * Sprite.SCALE);
  }

  public void stopMoving() {
    this.up = false;
    this.right = false;
    this.down = false;
    this.left = false;
  }

  public void update() {
    imageAnimationHandler();
    if(!alive) {
      if (deadAnime < dieAnimationTime) {
        deadAnime++;
      } else {
        removeFromBoard();
      }
      return;
    }
    AImove();
    moveHandler();
    collisionHandler();
  }

  public boolean canMove(int xS, int yS) {
    int topLeftX = x + xS + 1 * Sprite.SCALE;
    int topLeftY = y + yS + 1 * Sprite.SCALE;
    int topRightX = topLeftX + Sprite.SCALED_SIZE - (2 * Sprite.SCALE);
    int topRightY = topLeftY;
    int botLeftX = topLeftX;
    int botLeftY = topLeftY + Sprite.SCALED_SIZE - (2 * Sprite.SCALE);
    int botRightX = topRightX;
    int botRightY = botLeftY;


    Entity object = null;

    object = board.getStillObjectByCanvas(topLeftX, topLeftY);
    if((object instanceof Wall || object instanceof Brick || object instanceof Bomb)) return false;

    object = board.getStillObjectByCanvas(topRightX, topRightY);
    if((object instanceof Wall || object instanceof Brick || object instanceof Bomb)) return false;

    object = board.getStillObjectByCanvas(botLeftX, botLeftY);
    if((object instanceof Wall || object instanceof Brick || object instanceof Bomb)) return false;

    object = board.getStillObjectByCanvas(botRightX, botRightY);
    if((object instanceof Wall || object instanceof Brick || object instanceof Bomb)) return false;

    return true;
  }

  @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        gc.fillRect(realBodyRectangle.getX(), realBodyRectangle.getY(),realBodyRectangle.getWidth(),realBodyRectangle.getHeight());

    }

  public abstract void AImove();

  @Override
  public abstract void collide(Entity entity);

  @Override
  public abstract void imageAnimationHandler();
}
