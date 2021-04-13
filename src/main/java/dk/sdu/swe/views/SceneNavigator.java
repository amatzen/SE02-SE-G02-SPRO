package dk.sdu.swe.views;
// https://github.com/Marcotrombino/FXRouter
/*
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.util.Duration;
import javafx.animation.FadeTransition;


/**
 * FXRouter allows to manage scenes switching on JavaFX Application with an easy API
 * Inspired by Angular JS $routeProvider
 * @author Marco Trombino
 * @version 1.0.0
 */
public final class SceneNavigator {
    private static final String WINDOW_TITLE = "";
    private static final Double WINDOW_WIDTH = 800.0;
    private static final Double WINDOW_HEIGHT = 600.0;
    private static final Double FADE_ANIMATION_DURATION = 800.0;

    // FXRouter Singleton
    private static SceneNavigator router;
    // FXRouter Main Class reference to get main package
    private static Object mainRef;
    // FXRouter Application Stage reference to set scenes
    private static Stage window;

    // Application Stage title
    private static String windowTitle;
    // Application Stage size
    private static Double windowWidth;
    private static Double windowHeight;

    // routes switching animation
    private static String animationType;
    private static Double animationDuration;

    // FXRouter routes map
    private static Map<String, RouteScene> routes = new HashMap<>();
    // FXRouter current route
    private static RouteScene currentRoute;

    /**
     * FXRouter Inner Class used into routes map
     */
    private static class RouteScene {
        // route .fxml Scene path
        private String scenePath;
        // Scene (Stage) title
        private String windowTitle;
        private double sceneWidth;
        private double sceneHeight;
        // route data passed from goTo()
        private Object data;

        private RouteScene(String scenePath) {
            this(scenePath, getWindowTitle(), getWindowWidth(), getWindowHeight());
        }

        private RouteScene(String scenePath, String windowTitle) {
            this(scenePath, windowTitle, getWindowWidth(), getWindowHeight());
        }

        private RouteScene(String scenePath, double sceneWidth, double sceneHeight) {
            this(scenePath, getWindowTitle(), sceneWidth, sceneHeight);
        }

        /** Route scene constructor
         * @param scenePath: .FXML scene file
         * @param windowTitle: Scene (Stage) title
         * @param sceneWidth: Scene Width
         * @param sceneHeight: Scene Height
         */
        private RouteScene(String scenePath, String windowTitle, double sceneWidth, double sceneHeight) {
            this.scenePath = scenePath;
            this.windowTitle = windowTitle;
            this.sceneWidth = sceneWidth;
            this.sceneHeight = sceneHeight;
        }

        private static String getWindowTitle() {
            return SceneNavigator.windowTitle != null ? SceneNavigator.windowTitle : WINDOW_TITLE;
        }

        private static double getWindowWidth() {
            return SceneNavigator.windowWidth != null ? SceneNavigator.windowWidth : WINDOW_WIDTH;
        }

        private static double getWindowHeight() {
            return SceneNavigator.windowHeight != null ? SceneNavigator.windowHeight : WINDOW_HEIGHT;
        }
    }

    /**
     * FXRouter constructor kept private to apply Singleton pattern
     */
    private SceneNavigator() {}

    public static void bind(Object ref, Stage win) {
        checkInstances(ref, win);
    }

    public static void bind(Object ref, Stage win, String winTitle) {
        checkInstances(ref, win);
        windowTitle = winTitle;
    }

    public static void bind(Object ref, Stage win, double winWidth, double winHeight) {
        checkInstances(ref, win);
        windowWidth = winWidth;
        windowHeight = winHeight;
    }

    /** FXRouter binder with Application Stage and main package
     * @param ref: Main Class reference
     * @param win: Application Stage
     * @param winTitle: Application Stage title
     * @param winWidth: Application Stage width
     * @param winHeight: Application Stage height
     */
    public static void bind(Object ref, Stage win, String winTitle, double winWidth, double winHeight) {
        checkInstances(ref, win);
        windowTitle = winTitle;
        windowWidth = winWidth;
        windowHeight = winHeight;
    }

    /** set FXRouter references only if they are not set yet
     * @param ref: Main Class reference
     * @param win: Application Stage
     */
    private static void checkInstances(Object ref, Stage win) {
        if(mainRef == null)
            mainRef = ref;
        if(router == null)
            router = new SceneNavigator();
        if(window == null)
            window = win;
    }

    public static void when(String routeLabel, String scenePath) {
        RouteScene routeScene = new RouteScene(scenePath);
        routes.put(routeLabel, routeScene);
    }

    public static void when(String routeLabel, String scenePath, String winTitle) {
        RouteScene routeScene = new RouteScene(scenePath, winTitle);
        routes.put(routeLabel, routeScene);
    }

    public static void when(String routeLabel, String scenePath, double sceneWidth, double sceneHeight) {
        RouteScene routeScene = new RouteScene(scenePath, sceneWidth, sceneHeight);
        routes.put(routeLabel, routeScene);
    }

    /** Define a FXRouter route
     * @param routeLabel: Route label identifier
     * @param scenePath: .FXML scene file
     * @param winTitle: Scene (Stage) title
     * @param sceneWidth: Scene Width
     * @param sceneHeight: Scene Height
     */
    public static void when(String routeLabel, String scenePath, String winTitle, double sceneWidth, double sceneHeight) {
        RouteScene routeScene = new RouteScene(scenePath, winTitle, sceneWidth, sceneHeight);
        routes.put(routeLabel, routeScene);
    }

    public static void goTo(String routeLabel, boolean keepDimensions) throws IOException {
        // get corresponding route
        RouteScene route = routes.get(routeLabel);
        loadNewRoute(route, keepDimensions);
    }

    /** Switch between FXRouter route and show corresponding scenes
     * @param routeLabel: Route label identifier
     * @param data: Data passed to route
     * @throws Exception: throw FXMLLoader exception if file is not loaded correctly
     */
    public static void goTo(String routeLabel, Object data, boolean keepDimensions) throws IOException {
        // get corresponding route
        RouteScene route = routes.get(routeLabel);
        // set route data
        route.data = data;
        loadNewRoute(route, keepDimensions);
    }

    /** Helper method of goTo() which load and show new scene
     * @throws Exception: throw FXMLLoader exception if file is not loaded correctly
     */
    private static void loadNewRoute(RouteScene route, boolean keepDimensions) throws IOException {
        // set FXRouter current route reference
        currentRoute = route;

        // load .fxml resource
        Parent resource = FXMLLoader.load(Objects.requireNonNull(SceneNavigator.class.getClassLoader().getResource(route.scenePath)));

        // set window title from route settings or default setting
        window.setTitle(route.windowTitle);

        // set new route scene
        if (keepDimensions && window.getScene() != null) {
            window.setScene(new Scene(resource, window.getScene().getWidth(), window.getScene().getHeight()));
        } else {
            window.setScene(new Scene(resource, route.sceneWidth, route.sceneHeight));
        }

        // show the window
        window.show();

        // set scene animation
        routeAnimation(resource);
    }

    /* Syntactic sugar for goTo() method when FXRouter get set */
    public static void startFrom(String routeLabel) throws Exception {
        goTo(routeLabel, true);
    }

    public static void startFrom(String routeLabel, Object data) throws Exception {
        goTo(routeLabel, data, true);
    }

    /** set FXRouter switching animation
     * @param anType: Animation type
     */
    public static void setAnimationType(String anType) {
        animationType = anType;
    }

    /** set FXRouter switching animation
     * @param anType: Animation type
     * @param anDuration: Animation duration
     */
    public static void setAnimationType(String anType, double anDuration) {
        animationType = anType;
        animationDuration = anDuration;
    }

    /** Animate routes switching based on animation type
     * @param node: Parent node to animate
     */
    private static void routeAnimation(Parent node) {
        String anType = animationType != null ? animationType.toLowerCase() : "";
        switch(anType) {
            case "fade":
                Double fd = animationDuration != null ? animationDuration : FADE_ANIMATION_DURATION;
                FadeTransition ftCurrent = new FadeTransition(Duration.millis(fd), node);
                ftCurrent.setFromValue(0.0);
                ftCurrent.setToValue(1.0);
                ftCurrent.play();
                break;
            default:
                break;
        }
    }

    /** Get current route data
     */
    public static Object getData() {
        return currentRoute.data;
    }

}
