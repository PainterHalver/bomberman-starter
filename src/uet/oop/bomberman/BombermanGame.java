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

    public static final int WIDTH = 20;
    public static final int HEIGHT = 13;
    public int loopCount = 0;
    public long start = System.currentTimeMillis();
    
    private GraphicsContext gc;
    private Canvas canvas;
    private Pane screenPane;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * 31, Sprite.SCALED_SIZE * 13);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        screenPane = new Pane();
        screenPane.setMaxWidth(Sprite.SCALED_SIZE * 20);
        screenPane.setMaxHeight(Sprite.SCALED_SIZE * 13);
        screenPane.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(screenPane);


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
//                loopCount++;
//                if (System.currentTimeMillis() - start > 1000) {
//                    System.out.println(loopCount);
//                    loopCount = 0;
//                    start = System.currentTimeMillis();
//                }
                render();
                update();
            }
        };
        timer.start();

//        createMap();
        mapFromFile();


        Bomber bomberman = new Bomber(1, 2, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    bomberman.up = true;
                    break;
                case RIGHT:
                    bomberman.right = true;
                    break;
                case DOWN:
                    bomberman.down = true;
                    break;
                case LEFT:
                    bomberman.left = true;
                    break;
                default:
                    break;
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    bomberman.up = false;
                    break;
                case RIGHT:
                    bomberman.right = false;
                    break;
                case DOWN:
                    bomberman.down = false;
                    break;
                case LEFT:
                    bomberman.left = false;
                    break;
                default:
                    break;
            }
        });
    }

    public void mapFromFile() {
        Scanner scan = null;
        try {
            scan = new Scanner(new FileReader("C:\\Users\\Calputer\\Desktop\\OOP-Projects\\bomberman-starter\\res\\levels\\Level1.txt")).useDelimiter("\\A");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        while(true) {
            assert scan != null;
            if (!scan.hasNext()) break;
            sb.append(scan.next());
        }
        scan.close();
        //System.out.println(sb.toString());

        String[] lines = sb.toString().split("\n");
        String[] line1 = lines[0].split(" ");
        int level = Integer.parseInt(line1[0].trim());
        int rowCount = Integer.parseInt(line1[1].trim());
        int colCount = Integer.parseInt(line1[2].trim());
        Character[][] mapMatrix = new Character[rowCount][colCount];
        for(int i = 1; i<= rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                mapMatrix[i-1][j] = lines[i].charAt(j);
            }
        }
        //Create map from matrix
        for(int i = 0; i< rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                Entity object;
                char c = mapMatrix[i][j];
//                System.out.print(ANSI_GREEN + c + ANSI_RESET);
                switch (c) {
                    case '#':
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;
                    case '*':
                        object = new Wall(j, i, Sprite.brick.getFxImage());
                        break;
                    case 'x':
                        object = new Wall(j, i, Sprite.portal.getFxImage());
                        break;
                    case 'b':
                        object = new Wall(j, i, Sprite.powerup_bombs.getFxImage());
                        break;
                    case 'f':
                        object = new Wall(j, i, Sprite.powerup_flames.getFxImage());
                        break;
                    case 's':
                        object = new Wall(j, i, Sprite.powerup_speed.getFxImage());
                        break;
                    default:
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
            System.out.println();
        }
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
        log("Loaded map", ANSI_GREEN);
    }

    public void update() {
        entities.forEach(Entity::update);
        GameScreen.screenShiftHandler(entities,canvas, screenPane);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
