package dk.sdu.swe.views.partials;

import dk.sdu.swe.data.dao.UserDAOImpl;
import dk.sdu.swe.domain.controllers.AuthController;
import dk.sdu.swe.domain.controllers.UserController;
import dk.sdu.swe.domain.models.CompanyAdministrator;
import dk.sdu.swe.domain.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

public class UserListItem extends HBox {

    @FXML
    private Label emailLbl, usernameLbl;

    @FXML
    private CheckBox suspendedCheckbox, administratorCheckbox;

    private User user;

    private ListView<UserListItem> container;

    public UserListItem(User user, ListView<UserListItem> container) {
        this.user = user;
        this.container = container;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource(
                    "dk/sdu/swe/ui/admin/components/UserListItem.fxml")));
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

        if (user instanceof CompanyAdministrator) {
            administratorCheckbox.setSelected(true);
        }

        if (!AuthController.getInstance().getUser().hasPermission("companies.user.promote")) {
            administratorCheckbox.setDisable(true);
        }
    }

    @FXML
    private void onAdministratorChange(ActionEvent event) {

        if (administratorCheckbox.isSelected()) {
            UserDAOImpl.getInstance().promoteUser(user, "CompanyAdministrator");
        } else {
            UserDAOImpl.getInstance().promoteUser(user, "User");
        }

    }

    @FXML
    private void delete(ActionEvent event) {
        UserController.getInstance().delete(user);
        container.getItems().remove(this);
    }
}
