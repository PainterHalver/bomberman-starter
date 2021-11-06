package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.utils.TerminalColor.*;

public class Bomber extends Entity implements Moveable, Animatable {
    public boolean up, left, down, right;
    public int anime = 0;
    public boolean moving;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        animate();
        imageHandler();
        moveHandler();
    }

    @Override
    public void animate() {
        if(anime < 7500) anime++;
        else anime = 0;
    }


    @Override
    public void moveHandler() {
        int xS = 0, yS = 0;
        if (up) yS--;
        if (down) yS++;
        if (left) xS--;
        if (right) xS++;

        if (xS == 0 && yS == 0) {
            moving = false;
            return;
        }
        move(xS,yS);
        moving = true;
    }

    @Override
    public void move(double xS, double yS) {
        this.x += xS;
        this.y += yS;
    }

    @Override
    public void canMove(int x, int y) {

    }


    @Override
    public void imageHandler() {
        if(up) {
            this.img = Sprite.player_up.getFxImage();
            if (moving) {
                this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, anime, 20).getFxImage();
            }
        } else if (right) {
            this.img = Sprite.player_right.getFxImage();
            if (moving) {
                this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, anime, 20).getFxImage();
            }
        } else if (down) {
            this.img = Sprite.player_down.getFxImage();
            if (moving) {
                this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, anime, 20).getFxImage();
            }
        } else if (left) {
            this.img = Sprite.player_left.getFxImage();
            if (moving) {
                this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, anime, 20).getFxImage();
            }
        }
    }
}
