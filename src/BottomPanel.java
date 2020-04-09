import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BottomPanel {
    CentrePanel centrePanel = new CentrePanel();
    private MediaPlayer mediaPlayer = centrePanel.getMediaPlayer();
    public void buildBottom(BorderPane layout) {
        BorderPane bottomLayout = new BorderPane();
        bottomLayout.setId("bottomContainer");

        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);

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

        //play or pause track
        final ImageView[] playTrack = {null};
        String path = "assets/icon/play.png";
        try {
            playTrack[0] = new ImageView(new Image(new FileInputStream(path)));
            playTrack[0].setFitHeight(35);
            playTrack[0].setFitWidth(35);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button btnPlay = new Button();
        btnPlay.setGraphic(playTrack[0]);
        btnPlay.setAlignment(Pos.CENTER);
        btnPlay.setMaxWidth(Double.MAX_VALUE);
        btnPlay.setId("btnPlay");
        btnPlay.setOnAction(event -> {
            centrePanel.playVideo(layout);
            System.out.println(mediaPlayer.getStatus());
            if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                try{
                    playTrack[0] = new ImageView(new Image(new FileInputStream("assets/icon/pause.png")));
                    playTrack[0].setFitHeight(35);
                    playTrack[0].setFitWidth(35);
                    btnPlay.setGraphic(playTrack[0]);
                    mediaPlayer.pause();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if(mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                try{
                    playTrack[0] = new ImageView(new Image(new FileInputStream(path)));
                    playTrack[0].setFitHeight(35);
                    playTrack[0].setFitWidth(35);
                    btnPlay.setGraphic(playTrack[0]);
                    mediaPlayer.play(); // play video
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

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

        // Adding buttons to container
        bottomBox.getChildren().addAll(btnPrevious, btnPlay, btnNext);
        bottomLayout.setCenter(bottomBox);
        layout.setBottom(bottomLayout);
    }
}
