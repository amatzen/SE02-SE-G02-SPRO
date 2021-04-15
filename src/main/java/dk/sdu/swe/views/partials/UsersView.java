package dk.sdu.swe.views.partials;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class UsersView extends HBox {

    @FXML
    private Label userEmail, userName;

    @FXML
    private Checkbox SuspendedCheckbox, AdministratorCheckbox;

    public UsersView() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource(
                    "dk/sdu/swe/ui/admin/components/Users.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
