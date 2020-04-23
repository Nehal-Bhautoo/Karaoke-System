import javafx.scene.layout.BorderPane;
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
    private MediaPlayer.Status mediaPlayerStatus;
    final static String filePath = "list.txt";

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void setStatus(MediaPlayer.Status mediaPlayerStatus) {
        this.mediaPlayerStatus = mediaPlayerStatus;
    }

    public MediaPlayer.Status getMediaPlayer() {
        return mediaPlayerStatus;
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
        setStatus(mediaPlayerStatus = mediaPlayer.getStatus());
        mediaView.setMediaPlayer(mediaPlayer);

        //set mediaView in the centre of the gui
        layout.setCenter(mediaView);
    }
     // paused video
    public void pauseVideo() {
        mediaPlayer.pause();
    }

    //play video
    public void play() {
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
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
