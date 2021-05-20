package dk.sdu.swe.cross_cutting.helpers;

import dk.sdu.swe.persistence.DB;

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
        DB.resetSessionFactory();
    }

    public Environment getEnvironment() {
        return environment;
    }

}
