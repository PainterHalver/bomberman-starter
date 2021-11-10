package uet.oop.bomberman.entities.animatableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends AnimatableEntities{
  private int size = 1;
  private String direction = "CENTER";
  private boolean edge = false;

  public Flame(int boardX, int boardY, int size, Board board) {
    super(boardX, boardY, Sprite.bomb_exploded.getFxImage(), board);
    this.size = size;
    for (int i = 1; i < size; ++i) {
      board.addStillObject(new Flame(boardX, boardY - i, "UP", false));
      board.addStillObject(new Flame(boardX + i, boardY, "RIGHT", false));
      board.addStillObject(new Flame(boardX, boardY + i, "DOWN", false));
      board.addStillObject(new Flame(boardX - i, boardY, "LEFT", false));
    }
    board.addStillObject(new Flame(boardX, boardY - size, "UP", true));
    board.addStillObject(new Flame(boardX + size, boardY, "RIGHT", true));
    board.addStillObject(new Flame(boardX, boardY + size, "DOWN", true));
    board.addStillObject(new Flame(boardX - size, boardY, "LEFT", true));
  }

  private Flame(int boardX, int boardY, String direction, boolean isEdge) {
    super(boardX, boardY, Sprite.bomb_exploded.getFxImage());
    this.direction = direction;
    this.edge = isEdge;
  }

  @Override
  public void collide(Entity entity) {

  }

  public void animate() {
    if (!removedFromBoard){
      if (anime < 60) {
        anime++;
        imageAnimationHandler();
      } else {
        removedFromBoard = true;
      }
    }
  }

  @Override
  public void imageAnimationHandler() {
    this.img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, anime, 60).getFxImage();
  }

  @Override
  public void update() {
    animate();
  }
}
