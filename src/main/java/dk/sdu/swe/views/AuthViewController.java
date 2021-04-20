package dk.sdu.swe.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.controllers.AuthController;
import dk.sdu.swe.helpers.Environment;
import dk.sdu.swe.helpers.EnvironmentSelector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthViewController extends HBox {

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
    JFXComboBox<String> environmentSelector;

    public AuthViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/auth/auth-login.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     */
    @FXML
    private void initialize() {
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

        Router.getSceneRouter().goTo(AppViewController.class);
        //SceneNavigator.goTo("crms", true);
    }
}
