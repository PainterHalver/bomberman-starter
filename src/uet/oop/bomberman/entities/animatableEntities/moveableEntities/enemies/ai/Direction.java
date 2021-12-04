package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.ai;

import java.util.Random;

public enum Direction {
    // Value "NO" from the original Pascal code is replaced by null
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Direction reverse() {
        switch (this) {
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
        }
        return RIGHT;
    }

    public static Direction random() {
        Direction[] dirs = {UP, RIGHT, DOWN, LEFT};
        Random rand = new Random();
        return dirs[rand.nextInt(4)];
    }
}
