import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Implementation of the Interface that will run the app.
 * This implementation will display all the interface functionalities.
 * @author <Authors name>
 */
public class Interface extends Application {

    private Scene scene;
    public BorderPane layout;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        layout = new BorderPane();

        // give root node a CSS ID attribute
        layout.setId("appContainer");
        setSceneProperties();

        /*
         * Build app layout
         */
        BottomPanel bottomPanel = new BottomPanel();
        bottomPanel.buildBottom(layout);

        LeftPanel leftPanel = new LeftPanel();
        leftPanel.buildLeft(layout);

        leftPanel.listMusic(layout);

        // set properties of window application
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(new FileInputStream("assets/icon/icon.png")));
        primaryStage.setTitle("Karaoke Application");
        primaryStage.show();
    }

    /**
     * setSceneProperties. This simply sets app size.
     * It also is where the css stylesheet is attached to app.
     */

    private void setSceneProperties() {
        double percentageWidth = 0.68;
        double percentageHeight = 0.70;

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        percentageHeight *= screenSize.getHeight();
        percentageWidth *= screenSize.getWidth();
        this.scene = new Scene(layout, percentageWidth, percentageHeight);

        String cssFile = this.getClass().getResource("Style.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
    }
}
