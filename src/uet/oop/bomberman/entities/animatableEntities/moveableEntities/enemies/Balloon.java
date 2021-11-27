package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.MovableEntities;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {

  public Balloon(int boardX, int boardY, Image img, Board board) {
    super( boardX, boardY, img, board);
  }


  @Override
  public void AImove() {
    int move = (int) (System.currentTimeMillis() / 1000 % 4);
    switch (move){
      case 0:
        stopMoving();
        this.left = true;
        break;
      case 1:
        stopMoving();
        this.right = true;
        break;
      case 2:
        stopMoving();
        this.down = true;
        break;
      case 3:
        stopMoving();
        this.up = true;
        break;
    }
  }

  @Override
  public void collide(Entity entity) {

  }

  @Override
  public void imageAnimationHandler() {
    animate();

    if (!alive) {
      this.img = Sprite.enemyDeathSprite(Sprite.balloom_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, deadAnime, deadAnimeTime).getFxImage();
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
        this.img = Sprite.balloom_right1.getFxImage();
        if (moving) {
          this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, anime, 40).getFxImage();
        }
        break;
      default:
        this.img = Sprite.balloom_left1.getFxImage();
        if (moving) {
          this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, anime, 40).getFxImage();
        }
    }
  }
}
