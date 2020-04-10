import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CentrePanel {

    private MediaPlayer mediaPlayer;

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

        //set mediaView in the centre of the gui
        layout.setCenter(mediaView);
    }

    // display all data from array
    public void listMusicTitle() {
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
            System.out.println(author + " " + title+ " " + playTime+ " " + videoName);
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
            //System.out.println(songTitle);
            musics.setArray(songTitle);
        }
    }
}
