package uet.oop.bomberman.utils;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound;

import java.net.URL;
import java.util.ResourceBundle;

public class PauseMenuController implements Initializable {
  @FXML
  private Button btnNewGame;

  @FXML
  private Button btnResume;

  @FXML
  private Button btnExit;

  @FXML
  private Button btnRestart;

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

    btnResume.setOnAction(event -> {
      BombermanGame.resumeGame();
    });

    btnRestart.setOnAction(event -> {
      BombermanGame.resetStats();
      BombermanGame.loadGame(BombermanGame.level);
    });

    btnExit.setOnAction(event -> {
      Platform.exit();
    });

    sliderMusic.setValue(Sound.MUSIC_VOLUME * 100);
    sliderSfx.setValue(Sound.SFX_VOLUME * 100);

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

    sliderMusic.valueProperty().addListener((observable, oldValue, newValue) -> {
      Sound.setMusicVolume((double) newValue / 100);
    });

    sliderSfx.valueProperty().addListener((observable, oldValue, newValue) -> {
      Sound.setSfxVolume((double) newValue / 100);
    });
  }
}
