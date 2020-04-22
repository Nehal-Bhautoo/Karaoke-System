import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static javafx.geometry.Pos.TOP_CENTER;

/**
 * Implementation of the Left interface.
 * This implementation will display search button and list all music.
 * @author Nehal Bhautoo
 */
public class LeftPanel {

    private final Map<String, String> mapFile = CentrePanel.getTextFile();
    private final Set<String> suggestions = new HashSet<>();

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
                searchField();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //list all music buttons
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
            for(Map.Entry<String, String> entry : mapFile.entrySet()) {
                System.out.println(entry.getKey() + " => " + entry.getValue());
            }
        });

        // Adding all buttons to container
        buttonBox.getChildren().addAll(btnSearch, btnList);
        leftLayout.setCenter(buttonBox);
        layout.setLeft(leftLayout);
    }
     /**
      * Implementation of the search functionalities
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
        TextFields.bindAutoCompletion(searchField, suggestions);

        hBox.getChildren().addAll(label, separator, searchField);

        Scene scene = new Scene(hBox, 400, 200);
        stage.setScene(scene);
        stage.show();
    }
}
