import javafx.scene.control.Tooltip;
 /**
  * Implementing a hover message interface
  * @author <Authors name>
  */
public class HoverMessage {
    /**
     * This implementation will display a hint text,
     * when the cursor hover a button for a short period of time
     * @param message the text that will be displayed
     */
    public static Tooltip getTooltip(String message) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(message);
        tooltip.setId("toolTip");
        return tooltip;
    }
}
