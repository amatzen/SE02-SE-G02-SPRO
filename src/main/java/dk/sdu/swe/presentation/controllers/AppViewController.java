package dk.sdu.swe.presentation.controllers;

import dk.sdu.swe.presentation.Router;
import dk.sdu.swe.presentation.controllers.partials.Navbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

/**
 * The type App view controller.
 */
public class AppViewController extends VBox {

    @FXML
    private Pane navbarPane, contentPane;

    /**
     * Instantiates a new App view controller.
     */
    public AppViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/AppView.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        navbarPane.getChildren().setAll(new Navbar(new Router(contentPane)));
    }
}
