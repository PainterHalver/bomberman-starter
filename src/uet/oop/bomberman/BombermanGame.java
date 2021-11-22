package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

    public int loopCount = 0;
    public long start = System.currentTimeMillis();
    
    private GraphicsContext gc;
    private Canvas canvas;
    private Pane screenPane = new Pane();
    // Tao scene
    Scene scene = new Scene(screenPane);

    // Tao board
    Board board = new Board(scene, level);



    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao root container
        screenPane.setMaxWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        screenPane.setMaxHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * board.width, Sprite.SCALED_SIZE * board.height);
        gc = canvas.getGraphicsContext2D();
        screenPane.getChildren().add(canvas);

        // Them scene vao stage
        stage.setScene(scene);
        Pane pane = (Pane) scene.getRoot();
        pane.getChildren().add(new Label("Hello"));
        stage.show();

        String path = "res/sounds/03_Stage Theme.mp3";
        Media hit = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
//        mediaPlayer.play();
        new Thread(() -> {
            while (true) {
                if (mediaPlayer.getCurrentTime().toMillis() > 100) {
                    mediaPlayer.setVolume(0.1);
                    mediaPlayer.seek(new Duration(35000));
                    break;
                }
            }
        }).start();

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
                if (!running) {
                    screenPane.getChildren().setAll(new Label("GAME OVER"));
                    mediaPlayer.stop();
                    this.stop();
                }
                render();
                update();
            }
        };
        running = true;
        timer.start();

//        new Thread(() -> {
//            try {
//                Thread.sleep(3000);
//                timer.stop();
//                mediaPlayer.pause();
//                Thread.sleep(5000);
//                timer.start();
//                mediaPlayer.play();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
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
