package uet.oop.bomberman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sound {
  public static double MUSIC_VOLUME = 0.1;
  public static double SFX_VOLUME = 0.4;

  public static List<MediaPlayer> sfxs = new ArrayList<>();
  public static List<MediaPlayer> musics = new ArrayList<>();
  public static MediaPlayer backgroundMusic;

  public static void clearAll() {
    sfxs.clear();
    musics.clear();
  }

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
  public static MediaPlayer bomberDieFx = new MediaPlayer(new Media(new File("res/sounds/effects/bomber_die.mp3").toURI().toString()));

  /*
  |--------------------------------------------------------------------------
  | Musics
  |--------------------------------------------------------------------------
  */

  public static MediaPlayer menuMusic = new MediaPlayer(new Media(new File("res/sounds/1_title_screen.mp3").toURI().toString()));
  public static MediaPlayer stageStartMusic = new MediaPlayer(new Media(new File("res/sounds/2_stage_start.mp3").toURI().toString()));
  public static MediaPlayer stageThemeMusic = new MediaPlayer(new Media(new File("res/sounds/3_stage_theme.mp3").toURI().toString()));
  public static MediaPlayer stageCompleteMusic = new MediaPlayer(new Media(new File("res/sounds/5_stage_complete.mp3").toURI().toString()));
  public static MediaPlayer lifeLostMusic = new MediaPlayer(new Media(new File("res/sounds/8_life_lost.mp3").toURI().toString()));
  public static MediaPlayer gameOverMusic = new MediaPlayer(new Media(new File("res/sounds/9_game_over.mp3").toURI().toString()));
  public static MediaPlayer endingMusic = new MediaPlayer(new Media(new File("res/sounds/10_ending.mp3").toURI().toString()));

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

  public static void playMusic(MediaPlayer mediaPlayer) {
    musics.add(mediaPlayer);
    mediaPlayer.setVolume(MUSIC_VOLUME);
    mediaPlayer.setOnEndOfMedia(new Runnable() {
      public void run() {
        mediaPlayer.stop();
        musics.remove(mediaPlayer);
      }
    });
    mediaPlayer.play();
  }
  public static void playSFX(MediaPlayer mediaPlayer) {
    MediaPlayer m = new MediaPlayer(mediaPlayer.getMedia());

    sfxs.add(m);
    m.setVolume(SFX_VOLUME);
    m.setOnEndOfMedia(new Runnable() {
      public void run() {
        m.stop();
        sfxs.remove(mediaPlayer);
      }
    });
    m.play();
  }

  // Chỉ dùng cho tiếng bước chân, không dùng cho các tiếng khác
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

  public static void stopAll() {
    footstepHorizontalFx.stop();
    footstepVerticalFx.stop();
    if (backgroundMusic != null) {
      backgroundMusic.stop();
    }
    for (MediaPlayer m : sfxs) {
      m.stop();
    }
    for (MediaPlayer m : musics) {
      m.stop();
    }
  }

  public static void pauseAll(){
    footstepHorizontalFx.pause();
    footstepVerticalFx.pause();
//    if (backgroundMusic != null) {
//      backgroundMusic.pause();
//    }
    for (MediaPlayer m : sfxs) {
      m.pause();
    }
    for (MediaPlayer m : musics) {
      m.pause();
    }
  }

  public static void resumeAll() {
//    if (backgroundMusic != null) {
//      backgroundMusic.play();
//    }
    for (MediaPlayer m : sfxs) {
      m.play();
    }
    for (MediaPlayer m : musics) {
      m.play();
    }
  }

  public static void setSfxVolume(double volume) {
    SFX_VOLUME = volume;
    new Thread(() -> {
      footstepHorizontalFx.setVolume(SFX_VOLUME);
      footstepVerticalFx.setVolume(SFX_VOLUME);
      for (MediaPlayer m : sfxs) {
        m.setVolume(SFX_VOLUME);
      }
    }).start();
  }

  public static void setMusicVolume(double volume) {
    MUSIC_VOLUME = volume;
    new Thread(() -> {
      backgroundMusic.setVolume(MUSIC_VOLUME);
      for (MediaPlayer m : musics) {
        m.setVolume(MUSIC_VOLUME);
      }
    }).start();
  }

}
