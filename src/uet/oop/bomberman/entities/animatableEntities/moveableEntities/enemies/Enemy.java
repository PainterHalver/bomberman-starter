package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies;

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
    int topLeftX = x + xS;
    int topLeftY = y + yS;
    int topRightX = topLeftX + Sprite.SCALED_SIZE - (3 * Sprite.SCALE);
    int topRightY = topLeftY;
    int botLeftX = topLeftX;
    int botLeftY = topLeftY + Sprite.SCALED_SIZE - (3 * Sprite.SCALE);
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

  public abstract void AImove();

  @Override
  public abstract void collide(Entity entity);

  @Override
  public abstract void imageAnimationHandler();
}
