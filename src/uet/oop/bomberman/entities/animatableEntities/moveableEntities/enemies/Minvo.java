package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.ai.Direction;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Minvo extends Enemy {

  public Minvo(int boardX, int boardY, Image img, Board board) {
    super( boardX, boardY, img, board);
  }

  @Override
  public void AIMoveHandler() {
    Direction[] openDirections = ai.openDirections(lastDirection.reverse());
    Random rand = new Random();
    if (openDirections.length > 0) {
      Direction dir = openDirections[rand.nextInt(openDirections.length)];
      AIMove(dir);
    } else {
      if (ai.canMove(lastDirection)) {
        AIMove(lastDirection);
      } else {
        Direction dir = Direction.random();
        if (ai.canMove(dir)) {
          AIMove(dir);
        }
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
      this.img = Sprite.enemyDeathSprite(Sprite.minvo_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, deadAnime, deadAnimeTime).getFxImage();
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
        this.img = Sprite.minvo_right1.getFxImage();
        if (moving) {
          this.img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, anime, 40).getFxImage();
        }
        break;
      default:
        this.img = Sprite.minvo_left1.getFxImage();
        if (moving) {
          this.img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, anime, 40).getFxImage();
        }
    }
  }
}
