package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Oneal extends Entity implements Animatable, Moveable {

  public Oneal(int boardX, int boardY, Image img) {
    super( boardX, boardY, img);
  }

  @Override
  public void update() {

  }


  @Override
  public void animate() {

  }

  @Override
  public void animateImageHandler() {

  }

  @Override
  public void moveHandler() {

  }

  @Override
  public void move(double xS, double yS) {

  }

  @Override
  public boolean canMove(double xS, double yS) {
    return false;
  }
}
