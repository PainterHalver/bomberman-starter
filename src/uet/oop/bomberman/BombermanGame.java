package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.misc.IOUtils;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.GameScreen;

import static uet.oop.bomberman.utils.TerminalColor.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int SCREEN_WIDTH = 15;
    public static final int SCREEN_HEIGHT = 13;

    public int loopCount = 0;
    public long start = System.currentTimeMillis();
    
    private GraphicsContext gc;
    private Canvas canvas;
    private Pane screenPane = new Pane();
    // Tao scene
    Scene scene = new Scene(screenPane);

    // Tao board
    Board board = new Board(scene);


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao root container
        screenPane.setMaxWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        screenPane.setMaxHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * board.WIDTH, Sprite.SCALED_SIZE * board.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        screenPane.getChildren().add(canvas);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                loopCount++;
                if (System.currentTimeMillis() - start > 1000) {
                    log("FPS: " + loopCount, ANSI_BLUE);
                    stage.setTitle(loopCount + " FPS");
                    loopCount = 0;
                    start = System.currentTimeMillis();
                }
                render();
                update();
            }
        };
        timer.start();

    }

    public void update() {
        board.update();
        GameScreen.screenShiftHandler(board.getEntities(), canvas, screenPane);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        board.render(gc);
    }
}
