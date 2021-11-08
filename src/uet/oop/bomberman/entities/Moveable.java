package uet.oop.bomberman.entities;

public interface Moveable {
  boolean up = false, left = false, down = false, right = false;

  void moveHandler();
  void move(double xS, double yS);

  boolean canMove(double xS, double yS);
}
