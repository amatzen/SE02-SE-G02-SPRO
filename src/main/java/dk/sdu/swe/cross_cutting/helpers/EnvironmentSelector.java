package dk.sdu.swe.cross_cutting.helpers;

import dk.sdu.swe.persistence.DB;

/**
 * The type Environment selector.
 */
public class EnvironmentSelector {

    private static EnvironmentSelector environmentSelectorInstance;
    private volatile Environment environment;

    private EnvironmentSelector() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized EnvironmentSelector getInstance() {
        if (environmentSelectorInstance == null) {
            environmentSelectorInstance = new EnvironmentSelector();
        }
        return environmentSelectorInstance;
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Sets environment.
     *
     * @param input the input
     */
    public synchronized void setEnvironment(Environment input) {
        environment = input;
        DB.resetSessionFactory();
    }

}
