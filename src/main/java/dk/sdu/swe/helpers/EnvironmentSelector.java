package dk.sdu.swe.helpers;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.data.SeederUtility;

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