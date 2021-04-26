package dk.sdu.swe.views.partials;

import dk.sdu.swe.domain.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

public class UserListItem extends HBox {

    @FXML
    private Label emailLbl, usernameLbl;

    @FXML
    private CheckBox SuspendedCheckbox, AdministratorCheckbox;

    private User user;

    public UserListItem(User user) {
        this.user = user;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource(
                    "dk/sdu/swe/ui/admin/components/UsersListItem.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        usernameLbl.setText(user.getUsername());
        emailLbl.setText(user.getEmail());
    }
}
