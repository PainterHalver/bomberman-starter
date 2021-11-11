package uet.oop.bomberman.entities.stillEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;

public class Wall extends Entity {

    public Wall(int boardX, int boardY, Image img, Board board) {
        super( boardX, boardY, img, board);
    }

    @Override
    public void update() {

    }
}
