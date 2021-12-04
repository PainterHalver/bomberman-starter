package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.ai;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.animatableEntities.Bomb;
import uet.oop.bomberman.entities.animatableEntities.Brick;
import uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.Enemy;
import uet.oop.bomberman.entities.stillEntities.Wall;

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

}
