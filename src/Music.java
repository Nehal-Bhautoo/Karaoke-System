import java.util.List;

public class Music {
    private String songTitle;
    private String artist;
    private String playingTime;
    private String videoFileName;

    public Music(String songTitle, String artist, String playingTime, String videoFileName) {
        this.songTitle = songTitle;
        this.artist = artist;
        this.playingTime = playingTime;
        this.videoFileName = videoFileName;
    }

    public Music() {
        this.songTitle = "";
        this.artist = "";
        this.playingTime = "";
        this.videoFileName = "";
    }

    public Music(List<String> strings) {
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(String playingTime) {
        this.playingTime = playingTime;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    @Override
    public String toString() {
        return "Music{" +
                "songTitle='" + songTitle + '\'' +
                ", artist='" + artist + '\'' +
                ", playingTime='" + playingTime + '\'' +
                ", videoFileName='" + videoFileName + '\'' +
                '}';
    }
}
