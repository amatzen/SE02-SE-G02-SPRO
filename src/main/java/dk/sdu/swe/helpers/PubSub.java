package dk.sdu.swe.helpers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PubSub {

    private static volatile Map<String, List<Observer>> observerList = new HashMap<>();

    public static synchronized void subscribe(String topic, Observer observer) {
        if (!observerList.containsKey(topic)) {
            observerList.put(topic, new LinkedList<>());
        }
        observerList.get(topic).add(observer);
    }

    public static synchronized void unsubscribe(String topic, Observer observer) {
        observerList.remove(topic, observer);
    }

    public static void publish(String topic, Object payload) {
        observerList
            .entrySet()
            .stream()
            .filter(x -> x.getKey().equals(topic)).findAny()
            .map(Map.Entry::getValue)
            .ifPresent(l -> l.forEach(x -> x.onNotify(topic, payload)));
    }
}
