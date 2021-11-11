package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;

public class BombItem extends Entity {

  public BombItem(int boardX, int boardY, Image img, Board board) {
    super( boardX, boardY, img, board);
  }

  @Override
  public void update() {

  }
}
