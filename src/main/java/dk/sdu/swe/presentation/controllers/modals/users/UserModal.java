package dk.sdu.swe.presentation.controllers.modals.users;

import dk.sdu.swe.persistence.dao.UserDAOImpl;
import dk.sdu.swe.domain.controllers.UserController;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.IUserDAO;
import dk.sdu.swe.cross_cutting.exceptions.UserCreationException;
import dk.sdu.swe.presentation.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class UserModal extends Dialog<User> {

    private IUserDAO userDAO;

    private Company company;

    @FXML
    private TextField name, email, username;

    private GaussianBlur backgroundEffect;

    public UserModal(Window window, Company company) {
        userDAO = UserDAOImpl.getInstance();
        this.company = company;

        this.setResultConverter(param -> null);
        this.initOwner(window);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);

        backgroundEffect = new GaussianBlur(10);
        window.getScene().getRoot().setEffect(backgroundEffect);

        setOnCloseRequest((event) -> {
            getOwner().getScene().getRoot().setEffect(null);
        });

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/programmes/UserModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleClose(ActionEvent event) {
        getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        setResult(null);
        hide();
    }

    @FXML
    private void save(ActionEvent event) {
        String username = this.username.getText();
        String email = this.email.getText();
        String name = this.name.getText();
        try {
            User user = UserController.getInstance().createUser(username, email, name, company);
            setResult(user);
            hide();
        } catch (UserCreationException e) {
            AlertHelper.show(Alert.AlertType.ERROR, getOwner(), "Alert", e.getMessage());
        }
    }

}
