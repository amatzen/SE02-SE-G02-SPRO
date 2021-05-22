package dk.sdu.swe.cross_cutting.helpers;

/**
 * The interface Observer.
 */
public interface Observer {
    /**
     * On notify.
     *
     * @param topic   the topic
     * @param payload the payload
     */
    public void onNotify(String topic, Object payload);
}
