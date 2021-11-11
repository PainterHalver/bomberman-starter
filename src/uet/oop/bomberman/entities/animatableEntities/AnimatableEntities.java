package uet.oop.bomberman.entities.animatableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

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
    // Chỉ collide với Entity ở trên cùng

    List<Entity> entities = board.getEntities();
    for (int i = entities.size() - 1; i >=0; --i) {
      if (entities.get(i) == null) {
        continue;
      }
      if (entities.get(i) != this && this.realBodyRectangle.overlaps(entities.get(i).getRealBodyRectangle())) {
        collide(entities.get(i));
        break;
      }
    }
    List<Entity> stillObjects = board.getStillObjects();
    for (int i = stillObjects.size() - 1; i >=0; --i) {
      if (stillObjects.get(i) == null) {
        continue;
      }
      if (stillObjects.get(i) != this && this.realBodyRectangle.overlaps(stillObjects.get(i).getRealBodyRectangle())) {
        collide(stillObjects.get(i));
        break;
      }
    }
  }

  public abstract void collide(Entity entity);

  public abstract void imageAnimationHandler();

}
