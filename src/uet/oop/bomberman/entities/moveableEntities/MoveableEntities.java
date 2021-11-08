package uet.oop.bomberman.entities.moveableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class MoveableEntities extends Entity {
  protected boolean up = false, left = false, down = false, right = false;

  public MoveableEntities(int boardX, int boardY, Image img) {
    super(boardX, boardY, img);
  }

  public abstract void moveHandler();
  public abstract boolean canMove(int xS, int yS);
  public abstract void move(int xS, int yS);

}
