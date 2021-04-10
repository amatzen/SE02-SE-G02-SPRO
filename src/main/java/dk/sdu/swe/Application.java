package dk.sdu.swe;

import dk.sdu.swe.controllers.AuthController;
import dk.sdu.swe.views.SceneNavigator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class Application extends javafx.application.Application {
    public static void main(String[] args) throws Exception {

        AuthController authController = AuthController.getInstance();
        boolean signedIn = authController.signIn("almat20", "alexander");

        launch();
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("CrMS");

        SceneNavigator.bind(this, stage, "CrMS", 720, 420);
        SceneNavigator.when("login", "dk/sdu/swe/ui/auth/auth-login.fxml");
        SceneNavigator.when("crms", "dk/sdu/swe/ui/App.fxml");

        SceneNavigator.goTo("login");

        /*Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("dk/sdu/swe/ui/App.fxml")));
        Scene scene = new Scene(root);

        //scene.getStylesheets().add(getClass().getResource("ui/assets/style.css").toString());

        stage.setScene(scene);
        stage.show();*/

    }
}
