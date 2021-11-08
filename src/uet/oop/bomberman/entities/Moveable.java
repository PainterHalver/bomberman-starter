package uet.oop.bomberman.entities;

public interface Moveable {
  boolean up = false, left = false, down = false, right = false;

  void moveHandler();
  void move(int xS, int yS);

  boolean canMove(int xS, int yS);
}
