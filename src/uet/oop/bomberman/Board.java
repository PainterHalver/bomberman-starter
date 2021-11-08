package uet.oop.bomberman;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.GameScreen;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static uet.oop.bomberman.utils.TerminalColor.ANSI_GREEN;
import static uet.oop.bomberman.utils.TerminalColor.log;

public class Board {
  public final int WIDTH = 20;
  public final int HEIGHT = 13;
  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();
  private Scene scene = null;

  public Board(Scene scene) {
    this.scene = scene;
    loadLevel(1);
  }

  public Scene getScene() {
    return scene;
  }

  public List<Entity> getEntities() {
    return entities;
  }

  public List<Entity> getStillObjects() {
    return stillObjects;
  }

  public void loadLevel(int level) {
    Scanner scan = null;
    try {
      scan = new Scanner(new FileReader("./res/levels/Level"+ level + ".txt")).useDelimiter("\\A");
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
        stillObjects.add(new Grass(j, i ,Sprite.grass.getFxImage()));
      }
    }
    for(int i = 0; i< rowCount; ++i) {
      for (int j = 0; j < colCount; ++j) {
        Entity object = null;
        char c = mapMatrix[i][j];
        switch (c) {
          case '#':
            object = new Wall(j, i, Sprite.wall.getFxImage());
            break;
          case '*':
            object = new Wall(j, i, Sprite.brick.getFxImage());
            break;
          case 'x':
            object = new Wall(j, i, Sprite.portal.getFxImage());
            stillObjects.add(object);
            object = new Wall(j, i, Sprite.brick.getFxImage());
            break;
          case 'p':
            entities.add(new Bomber(j, i, Sprite.player_right.getFxImage(), this));
            break;
          case '1':
            entities.add(new Wall(j, i, Sprite.balloom_right1.getFxImage()));
            break;
          case '2':
            entities.add(new Wall(j, i, Sprite.oneal_right1.getFxImage()));
            break;
          case 'b':
            object = new Wall(j, i, Sprite.powerup_bombs.getFxImage());
            stillObjects.add(object);
            object = new Wall(j, i, Sprite.brick.getFxImage());
            break;
          case 'f':
            object = new Wall(j, i, Sprite.powerup_flames.getFxImage());
            stillObjects.add(object);
            object = new Wall(j, i, Sprite.brick.getFxImage());
            break;
          case 's':
            object = new Wall(j, i, Sprite.powerup_speed.getFxImage());
            stillObjects.add(object);
            object = new Wall(j, i, Sprite.brick.getFxImage());
            break;
          default:
            break;
        }
        if(object !=null) stillObjects.add(object);
      }
      System.out.println();
    }
  }

  public void update() {
    entities.forEach(Entity::update);
    stillObjects.forEach(Entity::update);
  }

  public void render(GraphicsContext gc) {
    // Vẽ Entities sau khi vẽ stillObjects
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
  }
}