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
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the Centre interface that will be displayed.
 * This implementation will display the Musics list and play video.
 * @author Nehal Bhautoo
 */
public class CentrePanel {

    private MediaPlayer mediaPlayer;
    private Duration duration;
    private Label playTime;
    private Slider slider;
    final static String filePath = "list.txt";


    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * get video file path using,
     * File() method.
     * Construct a new layout based on parameter.
     * @param layout The centre layout to be displayed.
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

        HBox mediaBar = new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5,10,5,10));
        BorderPane.setAlignment(mediaBar, Pos.CENTER);

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
                MediaPlayer mediaPlayer = getMediaPlayer();
                Duration currentTime = mediaPlayer.getCurrentTime();
                playTime.setText(String.valueOf(currentTime));
            });
        }
    }

    /**
     * Read content from text file and append the data in HashMap
     * @return the content of the file
     */
    public static Map<String, String> getTextFile() {
        Map<String, String> fileContent = new HashMap<>();
        BufferedReader bufferedReader = null;
        try {
            // file object
            File file = new File(filePath);

            //create BufferedReader object from the File
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            //read each line in text file
            while((line = bufferedReader.readLine()) != null) {
                //split line
                String[] parts = line.split(":");

                String musicTitle = parts[0].trim();
                String artist = parts[1].trim();

                //put song and artists in HashMap if not empty
                if(!musicTitle.equals("") && !artist.equals("")) {
                    fileContent.put(musicTitle, artist);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } return fileContent;
    }
}
