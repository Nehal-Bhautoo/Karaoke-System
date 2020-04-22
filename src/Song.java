public class Song {
    private String songTitle;
    private String author;
    private String playTime;
    private String music;

    public Song(String songTitle, String author, String playTime, String music) {
        this.songTitle = songTitle;
        this.author = author;
        this.playTime = playTime;
        this.music = music;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
