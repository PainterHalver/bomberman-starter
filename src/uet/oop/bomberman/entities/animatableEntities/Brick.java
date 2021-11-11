package uet.oop.bomberman.entities.animatableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends AnimatableEntities {
  private int time = 20;
  private boolean destroyed = false;
  public Brick(int boardX, int boardY, Image img, Board board) {
    super( boardX, boardY, img, board);
  }

  public void animate() {
    if (destroyed){
      if (anime < 60) {
        anime++;
        imageAnimationHandler();
      } else {
        removeFromBoard();
      }
    }
  }

  public void destroy() {
    destroyed = true;
  }

  @Override
  public void update() {
    animate();
    collisionHandler();
  }

  @Override
  public void collide(Entity entity) {

  }

  @Override
  public void imageAnimationHandler() {
    assert destroyed;

    this.img = Sprite.oneCycleMovingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, anime, time).getFxImage();
  }
}
