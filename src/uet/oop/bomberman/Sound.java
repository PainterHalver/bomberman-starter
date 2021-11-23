package uet.oop.bomberman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sound {
  public static final double MUSIC_VOLUME = 0.1;
  public static final double SFX_VOLUME = 0.4;

  public static List<MediaPlayer> soundFxs = new ArrayList<>();
  public static MediaPlayer backgroundMusic;

  /*
  |--------------------------------------------------------------------------
  | Sound Effects
  |--------------------------------------------------------------------------
  */
  public static MediaPlayer footstepHorizontalFx = new MediaPlayer(new Media(new File("res/sounds/effects/footstep_horizontal_new.mp3").toURI().toString()));
  public static MediaPlayer footstepVerticalFx = new MediaPlayer(new Media(new File("res/sounds/effects/footstep_vertical_new.mp3").toURI().toString()));
  public static MediaPlayer placeBombFx = new MediaPlayer(new Media(new File("res/sounds/effects/place_bomb.mp3").toURI().toString()));
  public static MediaPlayer boostedFx = new MediaPlayer(new Media(new File("res/sounds/effects/boosted_with_item.mp3").toURI().toString()));
  public static MediaPlayer bombExplodeFx = new MediaPlayer(new Media(new File("res/sounds/effects/bomb_explode.mp3").toURI().toString()));
  public static MediaPlayer stageClearedFx = new MediaPlayer(new Media(new File("res/sounds/effects/stage_cleared.mp3").toURI().toString()));

  /*
  |--------------------------------------------------------------------------
  | Musics
  |--------------------------------------------------------------------------
  */
  public static MediaPlayer stageThemeMusic = new MediaPlayer(new Media(new File("res/sounds/3_stage_theme.mp3").toURI().toString()));

  public static void playBackground(MediaPlayer music) {
    backgroundMusic = music;
    backgroundMusic.setVolume(MUSIC_VOLUME);
    backgroundMusic.setOnEndOfMedia(new Runnable() {
      public void run() {
        backgroundMusic.seek(Duration.ZERO);
      }
    });
    backgroundMusic.play();
  }

  public static void play(MediaPlayer mediaPlayer) {
    mediaPlayer.setVolume(SFX_VOLUME);
    mediaPlayer.seek(Duration.ZERO);
    mediaPlayer.play();
  }

  public static void infinitePlay(MediaPlayer mediaPlayer, double rate) {
    mediaPlayer.setVolume(SFX_VOLUME - 0.2);
    mediaPlayer.setRate(rate);
    mediaPlayer.setOnEndOfMedia(new Runnable() {
      public void run() {
        mediaPlayer.seek(Duration.ZERO);
      }
    });
    mediaPlayer.play();
  }

//  public static void play(MediaPlayer mediaPlayer, int times) {
//    for (int i = 0; i < times; ++i) {
//      mediaPlayer.setVolume(SFX_VOLUME);
//      mediaPlayer.play();
//      mediaPlayer.setOnEndOfMedia(new Runnable() {
//        public void run() {
//          mediaPlayer.seek(Duration.ZERO);
//          mediaPlayer.stop();
//        }
//      });
//    }
//  }

  public static void stopAll() {
    backgroundMusic.stop();
    for (MediaPlayer m : soundFxs) {
      m.stop();
    }
  }

  public static void pauseAll(){
    backgroundMusic.pause();
    for (MediaPlayer m : soundFxs) {
      m.pause();
    }
  }
}
