package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {
  private boolean opened = false;

  public Portal(int boardX, int boardY, Image img, Board board) {
    super( boardX, boardY, img, board);
  }

  public boolean isOpened() {
    return opened;
  }

  @Override
  public void update() {
    if (board.getEntities().size() > 1) return;
    if (!opened) {
      opened = true;
    }
  }
}
