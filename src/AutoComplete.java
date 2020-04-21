import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class is a TextField which implements an "autocomplete" functionality, based on a supplied list of entries.
 * @author Nehal Bhautoo
 */
public class AutoComplete extends TextField {
    // Existing entries
    private final SortedSet<String> entries;

    //popup used to select entries
    private final ContextMenu popUpEntries;

    public AutoComplete() {
        super();
        entries = new TreeSet<>();
        popUpEntries = new ContextMenu();
        textProperty().addListener((observable, oldValue, newValue) -> {
            if(getText().length() == 0) {
                popUpEntries.hide();
            } else {
                LinkedList<String> searchResult = new LinkedList<>(entries.subSet(getText(), getText() + Character.MAX_VALUE));
                if(entries.size() > 0) {
                    populatePopup(searchResult);
                    if(!entries.isEmpty()) {
                        popUpEntries.show(AutoComplete.this, Side.BOTTOM, 0, 0);
                    }
                } else {
                    popUpEntries.hide();
                }
            }
        });

        focusedProperty().addListener((observable, oldValue, newValue) -> popUpEntries.hide());
    }

    /**
     * Get the existing set of autocomplete entries.
     * @return The existing autocomplete entries.
     */
    public SortedSet<String> getEntries() {
        return entries;
    }

    /**
     * Populate the entry set with the given search results.  Display is limited to 10 entries, for performance.
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> items = new LinkedList<>();
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i<count; i++) {
            final String result = searchResult.get(i);
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(event -> {
                setText(result);
                popUpEntries.hide();
            });
            items.add(item);
        }
        popUpEntries.getItems().clear();
        popUpEntries.getItems().addAll(items);
    }
}
