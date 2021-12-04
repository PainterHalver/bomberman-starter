package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.ai.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy {
  public Doll(int boardX, int boardY, Image img, Board board) {
    super( boardX, boardY, img, board);
    this.lastDirection = Direction.RIGHT;
  }

  @Override
  public void AIMoveHandler() {
    if (ai.canMove(lastDirection)) {
      AIMove(lastDirection);
    } else {
      Direction dir = Direction.random();
      if (ai.canMove(dir)) {
        AIMove(dir);
      }
    }
  }

  @Override
  public void collide(Entity entity) {

  }

  @Override
  public void imageAnimationHandler() {
    animate();

    if (!alive) {
      this.img = Sprite.enemyDeathSprite(Sprite.doll_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, deadAnime, deadAnimeTime).getFxImage();
      return;
    }
    switch (facingDirection) {
      case "LEFT":
        this.lastHorizontalDirection = "LEFT";
        break;
      case "RIGHT":
        this.lastHorizontalDirection = "RIGHT";
        break;
      default:
        break;
    }

    switch (lastHorizontalDirection) {
      case "RIGHT":
        this.img = Sprite.doll_right1.getFxImage();
        if (moving) {
          this.img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, anime, 40).getFxImage();
        }
        break;
      default:
        this.img = Sprite.doll_left1.getFxImage();
        if (moving) {
          this.img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, anime, 40).getFxImage();
        }
    }
  }
}
