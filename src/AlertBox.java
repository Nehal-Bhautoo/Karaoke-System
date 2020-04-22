import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Implementing an alert box interface to display errors
 * @author Nehal Bhautoo
 */
public class AlertBox {
    public static Stage stage = new Stage();

    /**
     * Construct a new interface
     * @param message the message that will be displayed on the interface
     */
    public void alertBox(String message) {

        // Create new Stage
        Stage primaryStage = stage;

        // create hHox to hold the message
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setId("Box");

        GridPane box = new GridPane();
        box.setPadding(new Insets(20, 10, 10, 10));
        box.setVgap(2);
        box.setHgap(5);
        box.setAlignment(Pos.CENTER);

        Label title = new Label();
        title.setText(message);
        title.setId("title");
        title.setPadding(new Insets(20));
        titleBox.getChildren().add(title);

        HBox btnBox = new HBox();
        btnBox.setAlignment(Pos.CENTER);

        Button btnExit = new Button();
        btnExit.setText("EXIT");
        btnExit.setId("btnExit");
        // close the interface when clicked
        btnExit.setOnAction(e -> primaryStage.close());

        box.add(btnExit, 1, 5);

        btnBox.getChildren().add(box);

        VBox vBox = new VBox();
        vBox.setMinSize(300, 120);
        vBox.getChildren().addAll(titleBox, btnBox);
        vBox.setId("Box2");

        Scene scene = new Scene(vBox);

        //attach css file
        String css = this.getClass().getResource("Style.css").toExternalForm();
        primaryStage.setScene(scene);
        scene.getStylesheets().add(css);
        primaryStage.show();
    }
}
