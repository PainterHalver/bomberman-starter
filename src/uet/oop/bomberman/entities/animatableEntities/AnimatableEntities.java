package uet.oop.bomberman.entities.animatableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatableEntities extends Entity {
  protected int anime = 0;

  public AnimatableEntities(int boardX, int boardY, Image img, Board board) {
    super(boardX, boardY, img, board);
  }

  public void animate() {
    if(anime < 60) anime++;
    else anime = 0;
  }

  public void collisionHandler() {

    for (Entity entity : board.getEntities()) {
      if (entity == null) {
        continue;
      }
      if (entity != this && this.realBodyRectangle.overlaps(entity.getRealBodyRectangle())) {
        collide(entity);
      }
    }
    for (Entity entity : board.getStillObjects()) {
      if (entity == null) {
        continue;
      }
      if (entity != this && this.realBodyRectangle.overlaps(entity.getRealBodyRectangle())) {
        collide(entity);
      }
    }
  }

  public abstract void collide(Entity entity);

  public abstract void imageAnimationHandler();

}
