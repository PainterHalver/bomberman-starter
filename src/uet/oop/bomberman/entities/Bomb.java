package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatableEntities {
  private boolean exploded = false;

  public Bomb(int boardX, int boardY, Image img) {
    super(boardX, boardY, img);
  }

  @Override
  public void moveAnimationHandler() {
    if (!exploded) {
      this.img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, anime, 20).getFxImage();
    } else {
      this.img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, anime, 20).getFxImage();
    }
  }

  @Override
  public void update() {
    animate();
    moveAnimationHandler();
  }
}
