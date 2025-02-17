package uet.oop.bomberman.entities.animatableEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatableEntities {
  private boolean exploded = false;
  private final double animateTime = 2.65 * 60;

  public Bomb(int boardX, int boardY, Image img, Board board) {
    super(boardX, boardY, img, board);
    Sound.playSFX(Sound.placeBombFx);
  }

  public void animate() {
    if (!exploded) {
      if (anime < animateTime) {
        anime++;
        imageAnimationHandler();
      } else {
        explode();
      }
    }
  }

  @Override
  public void collide(Entity entity) {
    if (entity instanceof Flame) {
      explode();
    }
  }

  public void explode() {
    exploded = true;
    removeFromBoard();
    board.addStillObject(new Flame(boardX, boardY, Bomber.flameSize, board));
    Sound.playSFX(Sound.bombExplodeFx);
  }

  @Override
  public void imageAnimationHandler() {
    if (!exploded) {
      this.img = Sprite.outThenInSprite(Sprite.bomb_2, Sprite.bomb_1, Sprite.bomb, anime, (int)animateTime, 2).getFxImage();
    } else {
      this.img = Sprite.outThenInSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, anime, (int)animateTime, 2).getFxImage();
    }
  }

  @Override
  public void update() {
    animate();
    collisionHandler();
  }
}
