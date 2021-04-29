package dk.sdu.swe.views;

import dk.sdu.swe.helpers.PubSub;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Router {

    //private static Map<Class<? extends Parent>, Parent> components = new HashMap<>();
    private static Router sceneRouter;

    @FXML
    private Pane container;

    private boolean doFadeAnimation = false;

    public Router(Pane container) {
        this.container = container;
        //components = new HashMap<>();
    }

    private void goTo(Class<? extends Parent> componentClass, Pane container) {
        Parent component = null;

        /*if (components.containsKey(componentClass)) {
            component = components.get(componentClass);
        } else {*/
            try {
                component = componentClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        //}

        goTo(component, container);
    }

    private void goTo(Parent component, Pane container) {
        //components.put(component.getClass(), component);

        if (doFadeAnimation && container.getChildren().size() > 0 && !container.getChildren().contains(component)){
            fadeOut(component, container);
        } else {
            container.getChildren().setAll(component);
        }

        PubSub.publish("routeChange", component.getClass().getSimpleName());
    }

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

    public void goTo(Class<? extends Parent> componentClass) {
        goTo(componentClass, container);
    }

    public void goTo(Parent component) {
        goTo(component, container);
    }

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
