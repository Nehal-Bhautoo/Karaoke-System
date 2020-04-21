import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Interface extends Application {

    private Scene scene;
    public BorderPane layout;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        layout = new BorderPane();
        layout.setId("appContainer");
        setSceneProperties();

        BottomPanel bottomPanel = new BottomPanel();
        bottomPanel.buildBottom(layout);

        LeftPanel leftPanel = new LeftPanel();
        leftPanel.buildLeft(layout);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

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
