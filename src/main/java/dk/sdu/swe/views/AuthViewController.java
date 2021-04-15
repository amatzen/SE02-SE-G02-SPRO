package dk.sdu.swe.views;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.controllers.AuthController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthViewController implements Initializable {

    private String modelPassword;

    @FXML
    AnchorPane anchorPane;

    @FXML
    JFXButton button;

    @FXML
    TextField textField;

    @FXML
    PasswordField passwordField;

    @FXML
    Label showPswd;

    @FXML
    Button hs_button;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login();
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void onEnter(ActionEvent ae) {
        login();
    }

    public void login() {
        if (textField.getText().isEmpty()) {
            showAlert(
                Alert.AlertType.ERROR,
                anchorPane.getScene().getWindow(),
                "Fejl!",
                "Brugernavnfeltet er tomt"
            );
            return;
        }

        if (passwordField.getText().isEmpty()) {
            showAlert(
                Alert.AlertType.ERROR,
                anchorPane.getScene().getWindow(),
                "Fejl!",
                "Adgangskodefeltet er tomt"
            );
            return;
        }

        AuthController authController = AuthController.getInstance();
        boolean signIn = false;
        try {
            signIn = authController.signIn(textField.getText(), passwordField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!signIn) {
            showAlert(
                Alert.AlertType.ERROR,
                anchorPane.getScene().getWindow(),
                "Fejl!",
                "Brugernavn eller adgangskode forkert."
            );
            return;
        }

        try {
            SceneNavigator.goTo("crms", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hsButton(ActionEvent actionEvent) {
        char[] temp;
        temp = passwordField.getText().toCharArray();
        int length = temp.length;

        if (length < 1){
            //Do nothing

        } else {
            String code = passwordField.getCharacters().toString();
            passwordField.setText(code);
            showPswd.setText("(" + code + ")");
        }
    }
}
