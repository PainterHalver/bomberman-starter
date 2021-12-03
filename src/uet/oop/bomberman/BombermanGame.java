package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.GameScreen;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static uet.oop.bomberman.utils.TerminalColor.*;

public class BombermanGame extends Application {

    public static final int SCREEN_WIDTH = 15;
    public static final int SCREEN_HEIGHT = 13;

    public static boolean running = false;
    public static int level = 1;
    public static int totalLevels = new File("./res/levels").list().length; //this could cause trouble

    public static int loopCount = 0;
    public static long start = System.currentTimeMillis();

    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Stage screenStage;
    public static Pane screenPane = new Pane();
    // Tao scene
    public static Scene gameScene = new Scene(screenPane);

    // Tao board
    public static Board board = null;

    public static AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            loopCount++;
            System.out.println();
            if (System.currentTimeMillis() - start > 1000) {
                log("FPS: " + loopCount, ANSI_BLUE);
                screenStage.setTitle(loopCount + " FPS");
                loopCount = 0;
                start = System.currentTimeMillis();
            }
            if (!running) {
                this.stop();
                showGameOver();
                Sound.stopAll();
                Sound.clearAll();
                Sound.playMusic(Sound.gameOverMusic);
                Sound.gameOverMusic.setOnEndOfMedia(() -> {
                    Sound.gameOverMusic.stop();
                    showMainMenu();
                });
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
        stage.setResizable(false);
        screenStage = stage;
//        loadGame(screenStage, level);
        showMainMenu();
    }

    public static void showMainMenu() {
        Scene menuScene = null;
        try {
            URL url = new File("res/scenes/main-menu.fxml").toURI().toURL();
            menuScene = new Scene(FXMLLoader.load(url), Sprite.SCALED_SIZE * SCREEN_WIDTH, Sprite.SCALED_SIZE * SCREEN_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        screenStage.setScene(menuScene);
        screenStage.show();
        Sound.playBackground(Sound.menuMusic);
    }

    private static void showPauseMenu() {
        Scene pauseScene = null;
        try {
            URL url = new File("res/scenes/pause-menu.fxml").toURI().toURL();
            pauseScene = new Scene(FXMLLoader.load(url), Sprite.SCALED_SIZE * SCREEN_WIDTH, Sprite.SCALED_SIZE * SCREEN_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        screenStage.setScene(pauseScene);
        screenStage.show();
    }

    public static void pauseGame() {
        Sound.pauseAll();
        showPauseMenu();
        timer.stop();
    }

    public static void resumeGame() {
        Sound.resumeAll();
        screenStage.setScene(gameScene);
        screenStage.show();
        timer.start();
    }

    private static void showStageInfo() {
        Font font = Font.loadFont("file:res/PressStart2P-vaV7.ttf", 50);
        StackPane stageInfo = new StackPane();
        stageInfo.setMinWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        stageInfo.setMinHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);
        stageInfo.styleProperty().set("-fx-background-color: black;");
        Label stageLevel = new Label("Stage " + level);
        stageLevel.setFont(font);
        stageLevel.setTextFill(Color.color(1, 1, 1));
        StackPane.setAlignment(stageInfo, Pos.CENTER_LEFT);
        stageInfo.getChildren().add(stageLevel);
        gameScene.setRoot(stageInfo);
        screenStage.setScene(gameScene);
        screenStage.show();
    }

    private static void showGameOver() {
        Font font = Font.loadFont("file:res/PressStart2P-vaV7.ttf", 50);
        StackPane stageInfo = new StackPane();
        stageInfo.setMinWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        stageInfo.setMinHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);
        stageInfo.styleProperty().set("-fx-background-color: black;");
        Label stageLevel = new Label("Game Over");
        stageLevel.setFont(font);
        stageLevel.setTextFill(Color.color(1, 1, 1));
        StackPane.setAlignment(stageInfo, Pos.CENTER_LEFT);
        stageInfo.getChildren().add(stageLevel);
        gameScene.setRoot(stageInfo);
        screenStage.setScene(gameScene);
        screenStage.show();
    }

    private static void showEzGame() {
        Font font = Font.loadFont("file:res/PressStart2P-vaV7.ttf", 50);
        StackPane stageInfo = new StackPane();
        stageInfo.setMinWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        stageInfo.setMinHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);
        stageInfo.styleProperty().set("-fx-background-color: black;");
        Label stageLevel = new Label("Game is easy\n bye :P");
        stageLevel.setFont(font);
        stageLevel.setTextFill(Color.color(1, 1, 1));
        StackPane.setAlignment(stageInfo, Pos.CENTER_LEFT);
        stageInfo.getChildren().add(stageLevel);
        gameScene.setRoot(stageInfo);
        screenStage.setScene(gameScene);
        screenStage.show();
    }

    public static void loadGame(int curLevel) {
        // Dừng timer để xóa bỏ những gì còn lại từ level trước
        timer.stop();
        // Clear hết Sound còn lại tránh lỗi
        Sound.clearAll();

        if (curLevel > totalLevels) {
            showEzGame();
            Sound.stopAll();
            Sound.playMusic(Sound.endingMusic);
            Sound.endingMusic.setOnEndOfMedia(() -> {
                Sound.endingMusic.stop();
                Platform.exit();
                System.exit(0);
            });
            return;
        }

        level = curLevel;

        // Tao root container
        screenPane.setMaxWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        screenPane.setMaxHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);

        showStageInfo();

        Sound.stopAll();
        Sound.playMusic(Sound.stageStartMusic);
        Sound.stageStartMusic.setOnEndOfMedia(() -> {
            Sound.stageStartMusic.stop();
            Sound.musics.remove(Sound.stageStartMusic);
            board = new Board(gameScene, level);
            // Tao Canvas
            canvas = new Canvas(Sprite.SCALED_SIZE * board.width, Sprite.SCALED_SIZE * board.height);
            gc = canvas.getGraphicsContext2D();
            screenPane.getChildren().add(canvas);

            // Them scene vao stage
            gameScene.setRoot(screenPane);
            screenStage.setScene(gameScene);
            screenStage.show();

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
