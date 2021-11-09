package uet.oop.bomberman.entities.moveableEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Balloon extends MovableEntities {

  public Balloon(int boardX, int boardY, Image img) {
    super( boardX, boardY, img);
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
  public boolean canMoveBrickAndWall(int xS, int yS) {
    return false;
  }
}