package dk.sdu.swe.presentation;

import dk.sdu.swe.cross_cutting.helpers.PubSub;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.lang.reflect.InvocationTargetException;

public class Router {

    private static Router sceneRouter;

    @FXML
    private Pane container;

    private boolean doFadeAnimation = false;

    /**
     * Create instance of router with a given container to show content in.
     * @param container Container to fill with content.
     */
    public Router(Pane container) {
        this.container = container;
    }

    /**
     * Swap contents of given container with a new instance of given class type.
     * @param componentClass Class type of component.
     * @param container Container to fill with content.
     */
    private void goTo(Class<? extends Parent> componentClass, Pane container) {
        Parent component = null;

        try {
            component = componentClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException
            | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        goTo(component, container);
    }

    /**
     * Swap contents of given container to the given component instance.
     * @param component Content to show in container.
     * @param container Container to fill with content.
     */
    private void goTo(Parent component, Pane container) {
        //components.put(component.getClass(), component);

        if (doFadeAnimation && container.getChildren().size() > 0 && !container.getChildren().contains(component)){
            fadeOut(component, container);
        } else {
            container.getChildren().setAll(component);
        }

        PubSub.publish("routeChange", component.getClass().getSimpleName());
    }

    /**
     * Does a fade transition from the current content in the given container to the given component.
     * @param component Component to transition to.
     * @param container Container to fill with content.
     */
    private void fadeOut(Parent component, Pane container) {
        Pane paneToRemove = (Pane) container.getChildren().get(0);
        if (!paneToRemove.equals(component)) {
            container.getChildren().add(0, component);

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(300));
            fadeTransition.setOnFinished(event -> {
                component.setOpacity(1.0d);
                paneToRemove.setOpacity(1.0d);
                container.getChildren().setAll(component);
            });

            fadeTransition.setNode(paneToRemove);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }
    }

    /**
     * Swaps current content with a new instance of the given class type.
     * @param componentClass Class type of component.
     */
    public void goTo(Class<? extends Parent> componentClass) {
        goTo(componentClass, container);
    }

    /**
     * Swap contents of container with given Parent instance.
     * @param component New content.
     */
    public void goTo(Parent component) {
        goTo(component, container);
    }

    /**
     * Returns instance of Router, that controls the scene root.
     * @return Scene root Router
     */
    public static Router getSceneRouter() {
        return Router.sceneRouter;
    }

    public static void setSceneRouter(Router router) {
        Router.sceneRouter = router;
    }

    public void setFadeAnimation(boolean doFadeAnimation) {
        this.doFadeAnimation = doFadeAnimation;
    }
}
