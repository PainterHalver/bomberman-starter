package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Balloon extends Entity implements Moveable, Animatable {

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
