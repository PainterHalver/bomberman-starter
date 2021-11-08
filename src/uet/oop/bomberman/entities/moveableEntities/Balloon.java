package uet.oop.bomberman.entities.moveableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Animatable;
import uet.oop.bomberman.entities.Entity;

public class Balloon extends MoveableEntities implements Animatable {

  public Balloon(int boardX, int boardY, Image img) {
    super( boardX, boardY, img);
  }

  @Override
  public void animate() {

  }

  @Override
  public void animateImageHandler() {

  }

  @Override
  public void update() {

  }

  @Override
  public void moveHandler() {

  }

  @Override
  public void move(int xS, int yS) {

  }

  @Override
  public boolean canMove(int xS, int yS) {
    return false;
  }
}
