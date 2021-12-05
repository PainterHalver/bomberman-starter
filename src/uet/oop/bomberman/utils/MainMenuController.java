package uet.oop.bomberman.utils;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
  @FXML
  private Button btnNewGame;

  @FXML
  private Button btnContinue;

  @FXML
  private Button btnExit;

  @FXML
  private Button btnTestLevel;

  @FXML
  private Slider sliderMusic;

  @FXML
  private Slider sliderSfx;



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    btnNewGame.setOnAction(event -> {
      BombermanGame.resetStats();
      BombermanGame.loadGame(1);

    });

    if (BombermanGame.level > 1 && BombermanGame.level != 99) {
      btnContinue.setText(" Continue (Stage " + BombermanGame.level +")");
      btnContinue.setOnAction(event -> {
        BombermanGame.resetStats();
        BombermanGame.loadGame(BombermanGame.level);
      });
    } else {
      btnContinue.setDisable(true);
    }

    btnTestLevel.setOnAction(actionEvent -> {
      BombermanGame.resetStats();
      BombermanGame.loadGame(99);
      TextArea textArea = new TextArea();
      String everything = "";

      BufferedReader br = null;
      try {
        br = new BufferedReader(new FileReader("demo.txt"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
          sb.append(line);
          sb.append(System.lineSeparator());
          line = br.readLine();
        }
        everything = sb.toString();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      textArea.setText(everything);
      textArea.setWrapText(true);
      textArea.setStyle("-fx-font-size: 16");
      textArea.setEditable(false);

      Scene aboutScene = new Scene(textArea, 500, 550);
      Stage aboutStage = new Stage();
      aboutStage.setScene(aboutScene);
      aboutStage.setX(50);
      aboutStage.setY(200);
      aboutStage.show();
    });


    btnExit.setOnAction(event -> {
      Platform.exit();
    });

    sliderMusic.setValue(Sound.MUSIC_VOLUME * 100);
    sliderSfx.setValue(Sound.SFX_VOLUME * 100);

    sliderMusic.valueProperty().addListener((observable, oldValue, newValue) -> {
      Sound.setMusicVolume((double) newValue / 100);
    });

    sliderMusic.styleProperty().bind(Bindings.createStringBinding(() -> {
      double percentage = (sliderMusic.getValue() - sliderMusic.getMin()) / (sliderMusic.getMax() - sliderMusic.getMin()) * 100.0 ;
      return String.format("-slider-track-color: linear-gradient(to right, -slider-filled-track-color 0%%, "
                      + "-slider-filled-track-color %f%%, -fx-base %f%%, -fx-base 100%%);",
              percentage, percentage);
    }, sliderMusic.valueProperty(), sliderMusic.minProperty(), sliderMusic.maxProperty()));

    sliderSfx.styleProperty().bind(Bindings.createStringBinding(() -> {
      double percentage = (sliderSfx.getValue() - sliderSfx.getMin()) / (sliderSfx.getMax() - sliderSfx.getMin()) * 100.0 ;
      return String.format("-slider-track-color: linear-gradient(to right, -slider-filled-track-color 0%%, "
                      + "-slider-filled-track-color %f%%, -fx-base %f%%, -fx-base 100%%);",
              percentage, percentage);
    }, sliderSfx.valueProperty(), sliderSfx.minProperty(), sliderSfx.maxProperty()));

    sliderSfx.valueProperty().addListener((observable, oldValue, newValue) -> {
      Sound.setSfxVolume((double) newValue / 100);
    });
  }
}
