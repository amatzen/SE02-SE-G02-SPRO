package dk.sdu.swe.helpers;

public interface Observer {
    public void onNotify(String topic, Object payload);
}
