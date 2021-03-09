package dk.sdu.swe;

import io.javalin.Javalin;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Application extends javafx.application.Application {
    public static final int FRONTEND_PORT = 23335;

    public static void main(String[] args) {
        // Backend Thread


        // Frontend Thread
        new Thread(() -> {
            Server server = new Server(FRONTEND_PORT);

            WebAppContext webapp = new WebAppContext();
            webapp.setContextPath("/");
            webapp.setBaseResource(Resource.newClassPathResource("/frontend"));

            server.setHandler(webapp);
            try {
                server.start();
                server.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // GUI Thread
        launch(args);

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
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        String START_URL = "http://localhost:"+String.valueOf(FRONTEND_PORT)+"/";

        WebView webView = new WebView();
        webView.getEngine().load(START_URL);

        BorderPane root = new BorderPane(webView, null, null, null, null);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setTitle("CrMS");
        primaryStage.show();
    }
}
