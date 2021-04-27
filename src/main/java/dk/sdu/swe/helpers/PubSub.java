package dk.sdu.swe.helpers;

import java.util.HashMap;
import java.util.Map;

public class PubSub {

    private static volatile Map<String, Observer> observerList = new HashMap<>();

    public static synchronized void subscribe(String topic, Observer observer) {
        observerList.put(topic, observer);
    }

    public static synchronized void unsubscribe(String topic, Observer observer) {
        observerList.remove(topic, observer);
    }

    public static void publish(String topic, Object payload) {
        observerList
            .entrySet()
            .stream()
            .filter(x -> x.getKey() == topic)
            .map(Map.Entry::getValue)
            .forEach(x -> x.onNotify(topic, payload));
    }
}
