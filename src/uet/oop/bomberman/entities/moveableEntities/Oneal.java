package uet.oop.bomberman.entities.moveableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Oneal extends MovableEntities {

  public Oneal(int boardX, int boardY, Image img) {
    super( boardX, boardY, img);
  }

  @Override
  public void update() {

  }


  @Override
  public void animateImageHandler() {

  }

  @Override
  public void moveHandler() {

  }

  @Override
  public void move(int xS, int yS) {

  }

  @Override
  public boolean canMoveBrickAndWall(int xS, int yS) {
    return false;
  }

  @Override
  public void collisionHandler() {

  }

  @Override
  public void collide(Entity entity) {

  }
}
