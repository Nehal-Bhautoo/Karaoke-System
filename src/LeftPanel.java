import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static javafx.geometry.Pos.TOP_CENTER;

public class LeftPanel {

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
        btnList.setOnAction(event -> centrePanel.listMusicTitle());

        // Adding all buttons to container
        buttonBox.getChildren().addAll(btnSearch, btnList);
        leftLayout.setCenter(buttonBox);
        layout.setLeft(leftLayout);
    }

    public void searching() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            CentrePanel centrePanel = new CentrePanel();
            centrePanel.getSongTitle();
            List<String> songTitle = MusicArray.getMusic();
            SearchSong searchSong = new SearchSong(songTitle);
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
