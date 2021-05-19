package dk.sdu.swe.cross_cutting.helpers;

public interface Observer {
    public void onNotify(String topic, Object payload);
}
