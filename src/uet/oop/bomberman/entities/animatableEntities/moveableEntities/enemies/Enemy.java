package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.MovableEntities;

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

  public abstract void AImove();

  @Override
  public abstract void collide(Entity entity);

  @Override
  public abstract void imageAnimationHandler();
}
