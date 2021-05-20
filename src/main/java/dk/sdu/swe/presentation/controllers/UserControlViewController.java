package dk.sdu.swe.presentation.controllers;

import dk.sdu.swe.domain.controllers.UserController;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.presentation.controllers.partials.UserControlListItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * The type User control view controller.
 */
public class UserControlViewController extends VBox {

    @FXML
    private ListView<UserControlListItem> userList;

    /**
     * Instantiates a new User control view controller.
     */
    public UserControlViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/admin/components/UserControlView.fxml")));
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
        List<User> users = UserController.getInstance().getAll();
        for (User user : users) {
            userList.getItems().add(new UserControlListItem(user, userList));
        }
    }

}
