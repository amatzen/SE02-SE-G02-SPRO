package dk.sdu.swe.helpers;

public class EnvironmentSelector {

    private volatile Environment environment;

    private static EnvironmentSelector environmentSelectorInstance;

    private EnvironmentSelector() {
    }

    public static synchronized EnvironmentSelector getInstance() {
        if (environmentSelectorInstance == null) {
            environmentSelectorInstance = new EnvironmentSelector();
        }
        return environmentSelectorInstance;
    }

    public synchronized void setEnvironment(Environment input) {
        environment = input;
    }

    public Environment getEnvironment() {
        return environment;
    }

}