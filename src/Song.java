public class Song {
    private String songTitle;
    private String author;

    public Song(String songTitle, String author) {
        this.songTitle = songTitle;
        this.author = author;
    }

    public Song() {
        this.songTitle = "";
        this.author = "";
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
}
