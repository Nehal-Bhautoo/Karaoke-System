import java.util.LinkedList;

public class RemovePlaylist {
    Node head;
    AlertBox alertBox = new AlertBox();

    static class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
            next = null;
        }
    }

    /**
     * Given a reference (pointer to pointer) to the head of a list
     * and a position.
     * @param position deletes the node at the given position.
     */
    public void deleteNode(int position) {
        if(head == null) {
            alertBox.alertBox("List Empty");
        }

        // Store head node
        Node temp = head;

        // If head needs to be removed
        if (position == 0) {
            head = temp.next;   // Change head
            alertBox.alertBox("Node Head Remove");
        }

        // Find previous node of the node to be deleted
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        // If position is more than number of nodes
        if (temp == null || temp.next == null)
            alertBox.alertBox("Node not found");

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        assert temp != null;
        Node next = temp.next.next;

        // Unlink the deleted node from list
        temp.next = next;
    }

    public void removeSong(int node) {
        LeftPanel leftPanel = new LeftPanel();
        LinkedList<String> playlist = leftPanel.getPlaylist();
        playlist.remove(node);
        System.out.println(playlist);
    }
}
