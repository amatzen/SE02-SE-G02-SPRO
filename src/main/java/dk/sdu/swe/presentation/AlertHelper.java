package dk.sdu.swe.presentation;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertHelper {

    public static void show(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
