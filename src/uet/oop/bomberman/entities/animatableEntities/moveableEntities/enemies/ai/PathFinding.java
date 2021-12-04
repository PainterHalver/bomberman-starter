package uet.oop.bomberman.entities.animatableEntities.moveableEntities.enemies.ai;

import uet.oop.bomberman.Board;

import java.util.ArrayDeque;
import java.util.Queue;

public class PathFinding {

    public boolean[][] getLab(Board board) {
        boolean[][] lab = new boolean[board.height][board.width];
        for (int i = 0; i < board.height; i++) {
            for (int j = 0; j < board.width; j++) {
                if(board.isWall(j, i) || board.isBrick(j, i) || board.isBomb(j, i)) {
                    lab[i][j] = true;
                } else {
                    lab[i][j] = false;
                }
            }
        }
        return lab;
    }

    public Direction findShortestPathToBomber(Board board, int cx, int cy, int mx, int my) {
        Queue<Node> queue = new ArrayDeque<>();

        boolean[][] lab = getLab(board);
        boolean[][] discovered = new boolean[board.height][board.width];

        discovered[cy][cx] = true;
        queue.add(new Node(cx, cy, null));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (Direction dir : Direction.values()) {
                int newX = node.x + dir.getDx();
                int newY = node.y + dir.getDy();
                Direction newDir = node.initialDir == null ? dir : node.initialDir;

                if (newX == mx && newY == my) {
                    return newDir;
                }

                if (!lab[newY][newX] && !discovered[newY][newX]) {
                    discovered[newY][newX] = true;
                    queue.add(new Node(newX, newY, newDir));
                }
            }
        }
        return null;
    }

}

