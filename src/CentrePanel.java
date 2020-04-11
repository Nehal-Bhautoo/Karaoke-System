import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CentrePanel {

    private MediaPlayer mediaPlayer;
    private HBox mediaBar;
    private Duration duration;
    private Label playTime;
    private Slider slider;

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /*
     * get video file path using
     * File()
     * display video in the gui window
     */
    public void playVideo(BorderPane layout) {

        // get the video file path
        String path = new File("src/media/test.mp4").getAbsolutePath();
        Media media = new Media(new File(path).toURI().toString());
        MediaView mediaView = new MediaView();

        // play the video on the window
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        setMediaPlayer(mediaPlayer);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaBar = new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5,10,5,10));
        BorderPane.setAlignment(mediaBar, Pos.TOP_CENTER);

        //Add time label
        Label timeLabel = new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);

        //Add spacer
        Label spacer = new Label("     ");
        mediaBar.getChildren().add(spacer);

        //Add time Slider
        slider = new Slider();
        HBox.setHgrow(slider, Priority.ALWAYS);
        slider.setMaxWidth(Double.MAX_VALUE);
        slider.setMinWidth(50);
        slider.valueProperty().addListener(observable -> {
            if(slider.isValueChanging()) {
                //multiply duration by percentage calculated by slider position
                mediaPlayer.seek(duration.multiply(slider.getValue() / 100.0));
            }
        });
        mediaBar.getChildren().add(slider);

        // Add play time
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);

        updateValues();
        //set mediaView in the centre of the gui
        layout.setCenter(mediaView);
        layout.setTop(mediaBar);
    }

    protected void updateValues() {
        if (playTime != null && slider != null) {
            Platform.runLater(() -> {
                Duration currentTime = mediaPlayer.getCurrentTime();
                playTime.setText(formatTime(currentTime, duration));
                slider.setDisable(duration.isUnknown());
                if (!slider.isDisabled()
                        && duration.greaterThan(Duration.ZERO)
                        && !slider.isValueChanging()) {
                    slider.setValue(currentTime.divide(duration).toMillis()*100.0);
                }
            });
        }
    }

    /*
     * The format time method calculates
     * the elapsed time the media has been playing
     * and formats it to be displayed
     */
    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }

    // display all data from array
    public void listMusicTitle(BorderPane layout) {
        Music[] music;
        music = musicArray();
        String author;
        String title;
        String playTime;
        String videoName;
        for(int i = 0; i<readFile(); i++) {
            new Music(
                    title = music[i].getSongTitle(),
                    author = music[i].getArtist(),
                    playTime = music[i].getPlayingTime(),
                    videoName = music[i].getVideoFileName()
            );
        }
    }

    // opening text file
    private static int readFile() {
        int count = 0;
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("list.txt")));
            while((line = bufferedReader.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } return count;
    }

    // reading txt file
    // add data from file to array
    public static Music[] musicArray() {
        Music[] music = new Music[readFile()];
        String line;
        String[] array;

        int i =0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("list.txt")));
            while((line = bufferedReader.readLine()) != null) {
                array = line.split(":");
                music[i] = new Music();
                music[i].setSongTitle(array[0]);
                music[i].setArtist(array[1]);
                music[i].setPlayingTime(array[2]);
                music[i].setVideoFileName(array[3]);
                i++;
            } bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } return music;
    }

    public void getSongTitle() {
        MusicArray musics = new MusicArray();
        List<String> songTitle;
        Music[] music;
        music = musicArray();
        for(int i = 0; i<readFile(); i++) {
            new Music(
                songTitle = Collections.singletonList(music[i].getSongTitle())
            );
            musics.setArray(songTitle);
        }
    }
}
