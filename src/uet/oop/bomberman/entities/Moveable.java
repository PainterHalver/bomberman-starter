package uet.oop.bomberman.entities;

public interface Moveable {
  void moveHandler();
  void move(double xS, double yS);

  void canMove(int x, int y);
}
