import java.util.LinkedList;

public class GetPlaylist {
    private static final LinkedList<String> playlist = new LinkedList<>();

    public void addToPlaylist(String song) {
        playlist.add(song);
    }

    public static LinkedList<String> getLinkedList() {
        return playlist;
    }
}
