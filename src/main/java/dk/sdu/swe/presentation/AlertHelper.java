package dk.sdu.swe.presentation;

import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 * The type Alert helper.
 */
public class AlertHelper {

    /**
     * Show.
     *
     * @param alertType the alert type
     * @param owner     the owner
     * @param title     the title
     * @param message   the message
     */
    public static void show(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
