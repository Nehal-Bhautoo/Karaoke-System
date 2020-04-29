import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class BottomPanel {

    CentrePanel centrePanel = new CentrePanel();
    private LinkedList<String> playlist;
    private final Label songPlaying = new Label();

    public void bottomPanel(LinkedList<String> playlist) {
        this.playlist = playlist;
    }

    public void buildBottom(BorderPane layout) {
        BorderPane bottomLayout = new BorderPane();
        bottomLayout.setId("bottomContainer");
        bottomLayout.setMinHeight(25);

        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setPadding(new Insets(0,150,0,0));

        //play previous track
        ImageView playPrevious = null;
        try {
            playPrevious = new ImageView(new Image(new FileInputStream("assets/icon/backward.png")));
            playPrevious.setFitWidth(35);
            playPrevious.setFitHeight(35);
        } catch (FileNotFoundException e) {
            e.fillInStackTrace();
        }
        Button btnPrevious = new Button("", playPrevious);
        btnPrevious.setAlignment(Pos.CENTER);
        btnPrevious.setMaxWidth(Double.MAX_VALUE);
        btnPrevious.setId("btnPrevious");
        btnPrevious.setOnAction(event -> {
        });
        Tooltip play_previous_track = HoverMessage.getTooltip("Play Previous Track");
        btnPrevious.setTooltip(play_previous_track);

        //play or pause track
        ImageView playTrack = null;
        String pathPlay = "assets/icon/play.png";
        try {
            playTrack = new ImageView(new Image(new FileInputStream(pathPlay)));
            playTrack.setFitHeight(35);
            playTrack.setFitWidth(35);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button btnPlay = new Button();
        btnPlay.setGraphic(playTrack);
        btnPlay.setAlignment(Pos.CENTER);
        btnPlay.setMaxWidth(Double.MAX_VALUE);
        btnPlay.setId("btnPlay");
        btnPlay.setOnAction(event -> {
            centrePanel.playVideo(layout);
            String pathPause = "assets/icon/pause.png";
            MediaPlayer mediaPlayer = centrePanel.getMediaPlayer();
            ImageView pause = null;
            try {
                pause = new ImageView(new Image(new FileInputStream(pathPause)));
                pause.setFitHeight(35);
                pause.setFitWidth(35);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if(mediaPlayer.getStatus() == MediaPlayer.Status.UNKNOWN) {
                //centrePanel.pauseVideo();
                System.out.println(mediaPlayer.getStatus());
                btnPlay.setGraphic(pause);
            }
            System.out.println(mediaPlayer.getStatus());
            songPlaying.setText("-" + playlist.get(1) + "-");
        });
        Tooltip play = HoverMessage.getTooltip("Play/Pause");
        btnPlay.setTooltip(play);

        //play next track
        ImageView playNext = null;
        try {
            playNext = new ImageView(new Image(new FileInputStream("assets/icon/next.png")));
            playNext.setFitHeight(35);
            playNext.setFitWidth(35);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button btnNext = new Button("", playNext);
        btnNext.setAlignment(Pos.CENTER);
        btnNext.setMaxWidth(Double.MAX_VALUE);
        btnNext.setId("btnNext");
        Tooltip play_next_track = HoverMessage.getTooltip("Play Next Track");
        btnNext.setTooltip(play_next_track);
        btnNext.setOnAction(event -> System.out.println(playlist));

        HBox vBox = new HBox();
        vBox.setAlignment(Pos.CENTER_LEFT);

        Label trackPlaying = new Label("Now Playing");
        trackPlaying.setId("trackPlaying");
        trackPlaying.setAlignment(Pos.TOP_CENTER);

        Label separator = new Label("      ");

        //Now playing track
        ImageView nowPlaying = null;
        try{
            nowPlaying = new ImageView(new Image(new FileInputStream("assets/icon/now_playing.png")));
            nowPlaying.setFitHeight(40);
            nowPlaying.setFitWidth(40);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button playing = new Button();
        playing.setId("playing");
        playing.setGraphic(nowPlaying);
        playing.setAlignment(Pos.CENTER_LEFT);
        playing.setMaxWidth(Double.MAX_VALUE);
        playing.setOnAction(event -> System.out.println(playlist));

        songPlaying.setId("songPlaying");
        songPlaying.setAlignment(Pos.BOTTOM_CENTER);

        vBox.getChildren().addAll(playing, separator, trackPlaying, songPlaying);

        // Adding buttons to container
        bottomBox.getChildren().addAll(btnPrevious, btnPlay, btnNext);
        bottomLayout.setCenter(bottomBox);
        bottomLayout.setLeft(vBox);
        layout.setBottom(bottomLayout);
    }
}
