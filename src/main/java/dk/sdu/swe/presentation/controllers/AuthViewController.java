package dk.sdu.swe.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.domain.controllers.AuthController;
import dk.sdu.swe.cross_cutting.helpers.Environment;
import dk.sdu.swe.cross_cutting.helpers.EnvironmentSelector;
import dk.sdu.swe.presentation.AlertHelper;
import dk.sdu.swe.presentation.Router;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Auth view controller.
 */
public class AuthViewController extends HBox {

    private String modelPassword;

    /**
     * The Main.
     */
    @FXML
    VBox main;

    /**
     * The Button.
     */
    @FXML
    JFXButton button;

    /**
     * The Text field.
     */
    @FXML
    TextField textField;

    /**
     * The Password field.
     */
    @FXML
    PasswordField passwordField;

    /**
     * The Password field 1.
     */
    @FXML
    TextField passwordField1;

    /**
     * The Show pswd.
     */
    @FXML
    Label showPswd;

    /**
     * The Hs button.
     */
    @FXML
    Button hs_button;

    /**
     * The Environment selector.
     */
    @FXML
    JFXComboBox<String> environmentSelector;

    /**
     * Instantiates a new Auth view controller.
     */
    public AuthViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/auth/AuthView.fxml")));
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
        environmentSelector.setOnAction((event) -> {
                EnvironmentSelector.getInstance().setEnvironment(Environment.getEnvFromString(environmentSelector.valueProperty().getValue()));
        });

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

    /**
     * On enter.
     *
     * @param ae the ae
     */
    public void onEnter(ActionEvent ae) {
        login();
    }

    /**
     * Login.
     */
    public void login() {
        if (textField.getText().isEmpty()) {
            AlertHelper.show(
                Alert.AlertType.ERROR,
                main.getScene().getWindow(),
                "Fejl!",
                "Brugernavnfeltet er tomt"
            );
            return;
        }

        if (passwordField.getText().isEmpty()) {
            AlertHelper.show(
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
            AlertHelper.show(
                Alert.AlertType.ERROR,
                main.getScene().getWindow(),
                "Fejl!",
                "Brugernavn eller adgangskode forkert."
            );
            return;
        }

        Router.getSceneRouter().goTo(AppViewController.class);
    }
}
