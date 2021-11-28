package uet.oop.bomberman.utils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import uet.oop.bomberman.BombermanGame;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
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
    btnNewGame.setOnMouseClicked(event -> {
      BombermanGame.loadGame(BombermanGame.screenStage, BombermanGame.level);
    });

    msgLabel.setVisible(false);
    btnContinue.setOnMouseClicked(event -> {
      msgLabel.setVisible(!msgLabel.isVisible());
    });

    btnExit.setOnMouseClicked(event -> {
      Platform.exit();
    });
  }
}
