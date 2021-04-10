package dk.sdu.swe.views;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Router {

    private Map<Class<? extends Parent>, Parent> components;

    private Pane container;

    public Router(Pane container) {
        this.container = container;
        components = new HashMap<>();
    }

    public void goTo(Class<? extends Parent> componentClass) {
        Parent component = null;

        if (components.containsKey(componentClass)) {
            component = components.get(componentClass);
        } else {
            try {
                component = componentClass.getDeclaredConstructor().newInstance();
                components.put(componentClass, component);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        container.getChildren().setAll(component);
    }

}
