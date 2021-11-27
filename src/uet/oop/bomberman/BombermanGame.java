package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.GameScreen;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

import static uet.oop.bomberman.utils.TerminalColor.*;

public class BombermanGame extends Application {

    public static final int SCREEN_WIDTH = 15;
    public static final int SCREEN_HEIGHT = 13;

    public static boolean running = false;
    public static int level = 1;

    public static int loopCount = 0;
    public static long start = System.currentTimeMillis();

    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Stage screenStage;
    public static Pane screenPane = new Pane();
    // Tao scene
    public static Scene scene = new Scene(screenPane);

    // Tao board
    public static Board board = null;

    public static AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            loopCount++;
            if (System.currentTimeMillis() - start > 1000) {
                log("FPS: " + loopCount, ANSI_BLUE);
                screenStage.setTitle(loopCount + " FPS");
                loopCount = 0;
                start = System.currentTimeMillis();
            }
            if (!running) {
                screenPane.getChildren().setAll(new Label("GAME OVER"));
                Sound.stopAll();
                Sound.play(Sound.gameOverMusic);
                Sound.gameOverMusic.setOnEndOfMedia(() -> {
                    Platform.exit();
                    System.exit(0);
                });
                this.stop();
            }
            render();
            update();
        }
    };


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        screenStage = stage;
        loadGame(screenStage, level);
    }

    public static void loadGame(Stage stage, int curLevel) {
        level = curLevel;

        // Tao root container
        screenPane.setMaxWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        screenPane.setMaxHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);

        Pane stageInfo = new Pane();
        stageInfo.setMinWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        stageInfo.setMinHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);
        Label stageLevel = new Label("Stage " + level);
        stageInfo.getChildren().add(stageLevel);
        scene.setRoot(stageInfo);
        stage.setScene(scene);
        stage.show();

        Sound.stopAll();
        timer.stop();
        MediaPlayer music = Sound.stageStartMusic;
        music.play();
        music.setOnEndOfMedia(() -> {
            music.stop();
            board = new Board(scene, level);
            // Tao Canvas
            canvas = new Canvas(Sprite.SCALED_SIZE * board.width, Sprite.SCALED_SIZE * board.height);
            gc = canvas.getGraphicsContext2D();
            screenPane.getChildren().add(canvas);

            // Them scene vao stage
            scene.setRoot(screenPane);
            stage.setScene(scene);
            stage.show();

            running = true;
            timer.start();
        });
    }


    public static void update() {
        board.update();
        GameScreen.screenShiftHandler(board.getEntities(), canvas, screenPane);
    }

    public static void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        board.render(gc);
    }
}
