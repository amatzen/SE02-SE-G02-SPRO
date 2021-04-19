package dk.sdu.swe;

import dk.sdu.swe.helpers.EnvironmentSelector;
import dk.sdu.swe.helpers.Environment;
import dk.sdu.swe.views.SceneNavigator;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        EnvironmentSelector.getInstance().setEnvironment(switch (System.getenv("DEFAULT_ENVIRONMENT")) {
            case "local" -> Environment.LOCAL;
            case "prod" -> Environment.PROD;
            default -> Environment.FLATFILE;
        });

        launch();
    }

    private int width = 1500;
    private int height = 900;

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
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        disableWarning();

        stage.setTitle("CrMS");

        SceneNavigator.bind(stage, "CrMS", 1500, 900);
        SceneNavigator.setAnimationType("fade", 1000);
        SceneNavigator.when("login", "dk/sdu/swe/ui/auth/auth-login.fxml");
        SceneNavigator.when("crms", "dk/sdu/swe/ui/App.fxml");

        SceneNavigator.goTo("login", true);

        /*Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("dk/sdu/swe/ui/App.fxml")));
        Scene scene = new Scene(root);

        //scene.getStylesheets().add(getClass().getResource("ui/assets/style.css").toString());

        stage.setScene(scene);
        stage.show();*/

    }
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
