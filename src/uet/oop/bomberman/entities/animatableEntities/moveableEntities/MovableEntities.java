package uet.oop.bomberman.entities.animatableEntities.moveableEntities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.EntityRectangle;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.animatableEntities.AnimatableEntities;
import uet.oop.bomberman.entities.animatableEntities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.Enemy;
import uet.oop.bomberman.entities.stillEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public abstract class MovableEntities extends AnimatableEntities {
  protected boolean up = false, left = false, down = false, right = false;
  protected String facingDirection = "RIGHT";
  protected boolean moving;
  protected boolean alive = true;
  protected double speed = 2.0 / Sprite.SCALE;
  protected final int DIE_TIME_SECOND = 2;
  protected int deadAnimeTime = 60 * DIE_TIME_SECOND;

  public boolean isAlive() {
    return alive;
  }

  public MovableEntities(int boardX, int boardY, Image img, Board board) {
    super(boardX,boardY,img, board);
  }

  public void moveHandler() {
    int xS = 0, yS = 0;
    if (up) yS -= Math.ceil(speed * Sprite.SCALE);
    if (down) yS += Math.ceil(speed * Sprite.SCALE);
    if (left) xS -= Math.ceil(speed * Sprite.SCALE);
    if (right) xS += Math.ceil(speed * Sprite.SCALE);

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

  public void move(int xS, int yS) {
    // Chia ra làm 2 để xử lý ấn 2 nút khi 1 nút đang đâm đầu vào tường
    if (canMove(xS, 0)) {
      this.x += xS;
      realBodyRectangle.setX(realBodyRectangle.getX() + xS);
    }
    if (canMove(0, yS)) {
      this.y += yS;
      realBodyRectangle.setY(realBodyRectangle.getY() + yS);
    }

    // Cập nhật boardX,Y
//    if (x % Sprite.SCALED_SIZE >= 0 && x % Sprite.SCALED_SIZE <= Sprite.SCALE) {
//      boardX = x / Sprite.SCALED_SIZE;
//    }
//    if (y % Sprite.SCALED_SIZE >= 0 && y % Sprite.SCALED_SIZE <= Sprite.SCALE) {
//      boardY = y / Sprite.SCALED_SIZE;
//    }

    int boardPositionX = (int) (x + realBodyRectangle.getWidth() / 2) / Sprite.SCALED_SIZE;
    int boardPositionY = (int) (y + realBodyRectangle.getHeight() / 2) / Sprite.SCALED_SIZE;
    EntityRectangle candidate = new EntityRectangle(boardPositionX * Sprite.SCALED_SIZE, boardPositionY * Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    if (candidate.contains(this.realBodyRectangle)) {
      this.boardX = boardPositionX;
      this.boardY = boardPositionY;
    }
  }

//  public boolean canMove(int xS, int yS) {
//    int topLeftX = x + xS;
//    int topLeftY = y + yS;
//    int topRightX = topLeftX + Sprite.SCALED_SIZE - (3 * Sprite.SCALE);
//    int topRightY = topLeftY;
//    int botLeftX = topLeftX;
//    int botLeftY = topLeftY + Sprite.SCALED_SIZE - (3 * Sprite.SCALE);
//    int botRightX = topRightX;
//    int botRightY = botLeftY;
//
//
//    Entity object = null;
//
//    object = board.getStillObjectByCanvas(topLeftX, topLeftY);
//    if((object instanceof Wall || object instanceof Brick)) return false;
//
//    object = board.getStillObjectByCanvas(topRightX, topRightY);
//    if((object instanceof Wall || object instanceof Brick)) return false;
//
//    object = board.getStillObjectByCanvas(botLeftX, botLeftY);
//    if((object instanceof Wall || object instanceof Brick)) return false;
//
//    object = board.getStillObjectByCanvas(botRightX, botRightY);
//    if((object instanceof Wall || object instanceof Brick)) return false;
//
//    return true;
//  }

  public abstract boolean canMove(int xS, int yS);

  public void seftDestruct() {
    // Khi giết được chú quái cuối cùng, alive để chỉ gọi hàm 1 lần
    if (this instanceof Enemy && board.getEntities().size() == 2 && alive) {
      Sound.playMusic(Sound.stageClearedFx);
    }

    moving = false;
    alive = false;
    this.up = false;
    this.right = false;
    this.down = false;
    this.left = false;

  }

  public abstract void collide(Entity entity);

}
