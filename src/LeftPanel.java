import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static javafx.geometry.Pos.*;

/**
 * Implementation of the Left interface.
 * This implementation will display search button and list all music.
 * @author Nehal Bhautoo
 */
public class LeftPanel {

    private final Map<String, String> mapFile = CentrePanel.getTextFile();
    private final Set<String> suggestions = new HashSet<>();
    private final LinkedList<String> playlist = new LinkedList<>();
    AlertBox newAlertBox = new AlertBox();

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
            newAlertBox.alertBox("Search Icon Not Found!!!");
            e.printStackTrace();
        }
        Button btnSearch = new Button("", searchIcon);
        btnSearch.setAlignment(Pos.BASELINE_CENTER);
        btnSearch.setMaxWidth(Double.MAX_VALUE);
        btnSearch.setId("btnSearch");
        btnSearch.setOnAction(event -> searchField());
        Tooltip search = HoverMessage.getTooltip("Search Song");
        btnSearch.setTooltip(search);

        //list all music buttons
        ImageView listIcon = null;
        try {
            listIcon = new ImageView(new Image(new FileInputStream("assets/icon/list.png")));
            listIcon.setFitWidth(35);
            listIcon.setFitHeight(35);
        } catch (FileNotFoundException e) {
            newAlertBox.alertBox("List Icon Not Found!!!");
            e.printStackTrace();
        }
        Button btnList = new Button("", listIcon);
        btnList.setAlignment(Pos.BASELINE_CENTER);
        btnList.setMaxWidth(Double.MAX_VALUE);
        btnList.setId("btnList");
        btnList.setOnAction(event -> listMusic(layout));
        Tooltip listMusic = HoverMessage.getTooltip("List All Song");
        btnList.setTooltip(listMusic);

        // Playlist button
        ImageView playlist = null;
        try {
            playlist = new ImageView(new Image(new FileInputStream("assets/icon/playlist.png")));
            playlist.setFitHeight(35);
            playlist.setFitWidth(35);
        } catch (FileNotFoundException e) {
            newAlertBox.alertBox("Playlist Icon Not found");
        }
        Button btnPlayList = new Button("", playlist);
        btnPlayList.setAlignment(Pos.BASELINE_CENTER);
        btnPlayList.setMaxWidth(Double.MAX_VALUE);
        btnPlayList.setId("playListBtn");
        Tooltip playList = HoverMessage.getTooltip("PlayList");
        btnPlayList.setTooltip(playList);
        btnPlayList.setOnAction(event -> viewPlayList(layout));

        // Adding all buttons to container
        buttonBox.getChildren().addAll(btnSearch, btnList, btnPlayList);
        leftLayout.setCenter(buttonBox);
        layout.setLeft(leftLayout);
    }

    /**
     * List all the songs and authors
     * @param layout set the list in the centre panel
     */
    private void listMusic(BorderPane layout) {
        Label music = new Label("Song");
        music.setId("musicList");

        Label author = new Label("Artist");
        author.setId("authorList");

        VBox vBox = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setId("gridPane");

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(50);
        gridPane.setHgap(450);

        //Setting the Grid alignment
        gridPane.setAlignment(CENTER);

        // Add a separator line
        Separator separator = new Separator(Orientation.HORIZONTAL);
        separator.setId("separator");
        //Arranging all the nodes in the grid
        gridPane.add(music, 0, 0);
        gridPane.add(author, 1,0);

        vBox.getChildren().addAll(gridPane, separator);
        vBox.setAlignment(TOP_CENTER);

        layout.setCenter(vBox);
    }

    /**
     * List all the songs from the playlist
     * @param layout set the playlist in the centre panel
     */
    private void viewPlayList(BorderPane layout) {
        Label[] songPlaylist = new Label[10];
        Label title = new Label("Playlist");
        title.setId("playListTitle");


        VBox vBox = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setId("gridPane");

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setHgap(450);

        //Setting the Grid alignment
        gridPane.setAlignment(CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(title, 0, 0);

        VBox playListContainer = new VBox();
        playListContainer.setPadding(new Insets(20));
        playListContainer.setAlignment(BASELINE_LEFT);

        // for each element in linked list create a label
        for(int i = 0; i<playlist.size(); i++) {
            songPlaylist[i] = new Label();
            songPlaylist[i].setId("songPlaylist");
            songPlaylist[i].setText(playlist.get(i));
            playListContainer.getChildren().addAll(songPlaylist[i]);
        }

        vBox.getChildren().addAll(gridPane, playListContainer);
        vBox.setAlignment(TOP_CENTER);

        layout.setCenter(vBox);
    }

     /**
      * Implementation of the search interface
      */
    private void searchField() {

        Stage stage = new Stage();
        stage.setTitle("Search Song");

        Label label = new Label("Enter Song Title");
        label.setId("searchSong");
        Label separator = new Label("    ");

        // looping through HashMap
        for(Map.Entry<String, String> entry : mapFile.entrySet()) {
            // populate hashSet with hashMap value
            suggestions.addAll(Collections.singleton(entry.getValue()));
        }

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setId("searchContainer");

        TextField searchField = new TextField();
        searchField.setPromptText("Search Song");
        searchField.setId("searchField");
        TextFields.bindAutoCompletion(searchField, suggestions);

        Label separator2 = new Label("    ");

        Button addPlaylist = new Button();
        addPlaylist.setId("addPlaylist");
        addPlaylist.setText("Add to playlist");
        addPlaylist.setMaxWidth(Double.MAX_VALUE);
        addPlaylist.setOnAction(event -> {
            // get text from textfield
            String songTitle = searchField.getText();

            // validating searched song entered from textfield
            if(songTitle.equals("")) {
                newAlertBox.alertBox("Enter Song Title");
            } else {
                // add searched song to linklist
                playlist.add(songTitle);
                System.out.println(playlist);
                newAlertBox.alertBox(songTitle + " added to Playlist");
            }
        });
        hBox.getChildren().addAll(label, separator, searchField, separator2, addPlaylist);
        Scene scene = new Scene(hBox, 400, 200);

        // attach css file
        String cssFile = this.getClass().getResource("Style.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.show();
    }
}
