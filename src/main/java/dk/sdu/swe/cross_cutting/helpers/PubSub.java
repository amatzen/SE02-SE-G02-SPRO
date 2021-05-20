package dk.sdu.swe.cross_cutting.helpers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The type Pub sub.
 */
public class PubSub {

    private static volatile Map<String, List<Observer>> observerList = new HashMap<>();

    /**
     * Subscribe.
     *
     * @param topic    the topic
     * @param observer the observer
     */
    public static synchronized void subscribe(String topic, Observer observer) {
        if (!observerList.containsKey(topic)) {
            observerList.put(topic, new LinkedList<>());
        }
        observerList.get(topic).add(observer);
    }

    /**
     * Unsubscribe.
     *
     * @param topic    the topic
     * @param observer the observer
     */
    public static synchronized void unsubscribe(String topic, Observer observer) {
        observerList.remove(topic, observer);
    }

    /**
     * Publish.
     *
     * @param topic   the topic
     * @param payload the payload
     */
    public static void publish(String topic, Object payload) {
        observerList
            .entrySet()
            .stream()
            .filter(x -> x.getKey().equals(topic)).findAny()
            .map(Map.Entry::getValue)
            .ifPresent(l -> l.forEach(x -> x.onNotify(topic, payload)));
    }
}
