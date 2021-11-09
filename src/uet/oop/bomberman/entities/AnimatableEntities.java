package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class AnimatableEntities extends Entity {
  public int anime = 0;

  public AnimatableEntities(int boardX, int boardY, Image img) {
    super(boardX, boardY, img);
  }

  public void animate() {
    if(anime < 60) anime++;
    else anime = 0;
  }

  public abstract void moveAnimationHandler();

}
