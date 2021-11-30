package uet.oop.bomberman.utils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound;

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
  private Slider sliderMusic;

  @FXML
  private Slider sliderSfx;

  @FXML
  private Label msgLabel;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    btnNewGame.setOnAction(event -> {
      BombermanGame.loadGame(BombermanGame.level);
    });

    msgLabel.setVisible(false);
    btnContinue.setOnMouseClicked(event -> {
      msgLabel.setVisible(!msgLabel.isVisible());
    });

    btnExit.setOnAction(event -> {
      Platform.exit();
    });

    sliderMusic.setValue(Sound.MUSIC_VOLUME * 100);
    sliderSfx.setValue(Sound.SFX_VOLUME * 100);

    sliderMusic.valueProperty().addListener((observable, oldValue, newValue) -> {
      Sound.MUSIC_VOLUME = (double) newValue / 100;
    });

    sliderSfx.valueProperty().addListener((observable, oldValue, newValue) -> {
      Sound.SFX_VOLUME = (double) newValue / 100;
    });
  }
}
