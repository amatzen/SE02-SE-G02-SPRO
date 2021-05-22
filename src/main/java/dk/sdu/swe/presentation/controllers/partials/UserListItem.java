package dk.sdu.swe.presentation.controllers.partials;

import dk.sdu.swe.domain.controllers.AuthController;
import dk.sdu.swe.domain.controllers.CompanyController;
import dk.sdu.swe.domain.controllers.UserController;
import dk.sdu.swe.domain.models.CompanyAdministrator;
import dk.sdu.swe.domain.models.SystemAdministrator;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.persistence.dao.UserDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

/**
 * The type User list item.
 */
public class UserListItem extends HBox {

    @FXML
    private Label emailLbl, usernameLbl;

    @FXML
    private CheckBox suspendedCheckbox, administratorCheckbox;

    @FXML
    private Button deleteBtn;

    private User user;

    private ListView<UserListItem> container;

    /**
     * Instantiates a new User list item.
     *
     * @param user      the user
     * @param container the container
     */
    public UserListItem(User user, ListView<UserListItem> container) {
        this.user = user;
        this.container = container;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource(
                        "dk/sdu/swe/presentation/views/admin/components/UserListItem.fxml")));
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

        if (user instanceof SystemAdministrator) {
            administratorCheckbox.setDisable(true);
            deleteBtn.setDisable(true);
        }

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

        CompanyController.getInstance().update(user.getCompany());

    }

    @FXML
    private void delete(ActionEvent event) {
        UserController.getInstance().delete(user);
        container.getItems().remove(this);
    }
}
