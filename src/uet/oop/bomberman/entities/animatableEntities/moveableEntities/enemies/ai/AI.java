package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.ai;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.Bomb;
import uet.oop.bomberman.entities.animatableEntities.Brick;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.Enemy;
import uet.oop.bomberman.entities.stillEntities.Wall;

import java.util.ArrayList;
import java.util.List;

public class AI {
  protected Enemy enemy = null;
  protected Board board = null;

  public AI (Enemy e, Board board) {
    this.enemy = e;
    this.board = board;
  }

  public boolean canMove(Direction d) {
    Entity object = board.getStillObjectByBoard(enemy.getBoardX() + d.getDx(), enemy.getBoardY() + d.getDy());
    return !(object instanceof Wall) && !(object instanceof Brick) && !(object instanceof Bomb);
  }

  public Direction[] openDirections(Direction currentDirection) {
    List<Direction> rtn = new ArrayList<>();
    if (canMove(Direction.UP)) {
      rtn.add(Direction.UP);
    }
    if (canMove(Direction.RIGHT)) {
      rtn.add(Direction.RIGHT);
    }
    if (canMove(Direction.DOWN)) {
      rtn.add(Direction.DOWN);
    }
    if (canMove(Direction.LEFT)) {
      rtn.add(Direction.LEFT);
    }
    rtn.removeIf(direction -> direction.equals(currentDirection));
    return rtn.toArray(new Direction[0]);
  }
}
