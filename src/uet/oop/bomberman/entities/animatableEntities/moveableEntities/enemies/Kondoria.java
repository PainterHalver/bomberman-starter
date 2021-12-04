package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.ai.Direction;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.ai.PathFinding;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
  public Kondoria(int boardX, int boardY, Image img, Board board) {
    super( boardX, boardY, img, board);
  }

  @Override
  public void AIMoveHandler() {
    PathFinding path = new PathFinding();

    Entity bomber = this.board.getBomber();
    Direction direction = null;
    direction = path.findShortestPathToBomber(this.board, this.boardX, this.boardY, bomber.getBoardX(), bomber.getBoardY());
    System.out.println(direction);

    AIMove(direction);
//    int boardPositionX = (int) (x + realBodyRectangle.getWidth() / 2) / Sprite.SCALED_SIZE;
//    int boardPositionY = (int) (y + realBodyRectangle.getHeight() / 2) / Sprite.SCALED_SIZE;
//    EntityRectangle candidate = new EntityRectangle(boardPositionX * Sprite.SCALED_SIZE, boardPositionY * Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
//    if (candidate.contains(this.realBodyRectangle)) {
//      AIMove(direction);
//    } else {
//      AIMove(lastDirection);
//    }
  }

  @Override
  public void collide(Entity entity) {

  }

  @Override
  public void imageAnimationHandler() {
    animate();

    if (!alive) {
      this.img = Sprite.enemyDeathSprite(Sprite.kondoria_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, deadAnime, deadAnimeTime).getFxImage();
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
        this.img = Sprite.kondoria_right1.getFxImage();
        if (moving) {
          this.img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, anime, 40).getFxImage();
        }
        break;
      default:
        this.img = Sprite.kondoria_left1.getFxImage();
        if (moving) {
          this.img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, anime, 40).getFxImage();
        }
    }
  }
}
