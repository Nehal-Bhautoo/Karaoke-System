import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;

import java.util.LinkedList;

/**
 * This class implements a delete method
 * that remove na element from a linked list.
 * @author <Authors name>
 */
public class MyEventHandler implements EventHandler<Event> {
    private final LinkedList<String> playlist;
    private final LinkedList<String> temp = GetPlaylist.getLinkedList();
    AlertBox alertBox = new AlertBox();

    public MyEventHandler(LinkedList<String> playlist) {
        this.playlist = playlist;
    }

    public void handle(Event event) {
        int source = Integer.parseInt(((Control)event.getSource()).getId());
        alertBox.alertBox(playlist.get(source) + " remove from playlist");
        playlist.remove(source);
        temp.remove(source);
    }
}