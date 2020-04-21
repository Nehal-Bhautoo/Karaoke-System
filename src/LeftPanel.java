import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;

import static javafx.geometry.Pos.TOP_CENTER;

/**
 * Implementation of the Left interface.
 * This implementation will display search button and list all music.
 * @author Nehal Bhautoo
 */
public class LeftPanel {

    /**
     * This method add all the buttons that the user will interact with.
     * @param layout the left layout
     */
    public void buildLeft(BorderPane layout) {
        BorderPane leftLayout = new BorderPane();

        // Left container to hold all buttons
        VBox buttonBox = new VBox();
        buttonBox.setMinWidth(20);
        buttonBox.setAlignment(TOP_CENTER);
        buttonBox.setId("buttonContainer");
        buttonBox.setSpacing(10);


        // Search button
        ImageView searchIcon = null;
        try {
            searchIcon = new ImageView(new Image(new FileInputStream("assets/icon/search.png")));
            searchIcon.setFitHeight(35);
            searchIcon.setFitWidth(35);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button btnSearch = new Button("", searchIcon);
        btnSearch.setAlignment(Pos.BASELINE_CENTER);
        btnSearch.setMaxWidth(Double.MAX_VALUE);
        btnSearch.setId("btnSearch");
        btnSearch.setOnAction(event -> {
            try {
                searching();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //list all music buttons
        CentrePanel centrePanel = new CentrePanel();
        ImageView listIcon = null;
        try {
            listIcon = new ImageView(new Image(new FileInputStream("assets/icon/list.png")));
            listIcon.setFitWidth(35);
            listIcon.setFitHeight(35);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button btnList = new Button("", listIcon);
        btnList.setAlignment(Pos.BASELINE_CENTER);
        btnList.setMaxWidth(Double.MAX_VALUE);
        btnList.setId("btnList");
        btnList.setOnAction(event -> {
            Map<String, String> mapFile = CentrePanel.getTextFile();
            for(Map.Entry<String, String> entry : mapFile.entrySet()) {
                System.out.println(entry.getKey() + " => " + entry.getValue());
            }
        });

        // Adding all buttons to container
        buttonBox.getChildren().addAll(btnSearch, btnList);
        leftLayout.setCenter(buttonBox);
        layout.setLeft(leftLayout);
    }

    public void searching() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            Map<String, String> songTitle = CentrePanel.getTextFile();
            SearchSong searchSong = null;
            for(Map.Entry<String, String> entry : songTitle.entrySet()) {
                searchSong = new SearchSong(Collections.singletonList(entry.getKey()));
            }
            AutoCompleteBox autoCompleteBox = new AutoCompleteBox(searchSong);
            JFrame frame = new JFrame();
            frame.add(autoCompleteBox);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
