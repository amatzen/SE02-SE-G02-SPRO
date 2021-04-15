package dk.sdu.swe.views;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.controllers.AuthController;
import dk.sdu.swe.helpers.Environment;
import dk.sdu.swe.helpers.EnvironmentSelector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AuthViewController implements Initializable {

    private String modelPassword;

    @FXML
    VBox main;

    @FXML
    JFXButton button;

    @FXML
    TextField textField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField passwordField1;

    @FXML
    Label showPswd;

    @FXML
    Button hs_button;

    @FXML
    ChoiceBox<String> environmentSelector;

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
        // Database Type
        List<String> environments = new ArrayList<>();
        for (Environment value : Environment.values()) {
            environments.add(value.getLabel());
        }
        environmentSelector.setItems(FXCollections.observableArrayList(environments));
        environmentSelector.valueProperty().setValue(EnvironmentSelector.getInstance().getEnvironment().getLabel());


        // Login button
        button.setOnAction(event -> login());

        // Password Masking Function
        this.passwordField1.setVisible(false);
        hs_button.setOnAction(event -> togglePasswordVisibility());
        this.passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.passwordField1.setText(newValue);
        });

        this.passwordField1.textProperty().addListener(((observable, oldValue, newValue) -> {
            this.passwordField.setText(newValue);
        }));
        // End Password Masking Function
    }

    private void togglePasswordVisibility() {
        if(this.passwordField.isVisible()) {
            this.passwordField.setVisible(false);
            this.passwordField1.setVisible(true);
        } else {
            this.passwordField1.setVisible(false);
            this.passwordField.setVisible(true);
        }
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
                main.getScene().getWindow(),
                "Fejl!",
                "Brugernavnfeltet er tomt"
            );
            return;
        }

        if (passwordField.getText().isEmpty()) {
            showAlert(
                Alert.AlertType.ERROR,
                main.getScene().getWindow(),
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
                main.getScene().getWindow(),
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
}
