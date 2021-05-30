package dk.sdu.swe.cross_cutting.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The enum Environment.
 */
public enum Environment {
    /**
     * Prod environment.
     */
    PROD("Production"),
    /**
     * Local environment.
     */
    LOCAL("Developer (Postgres)"),
    /**
     * Flatfile environment.
     */
    FLATFILE("Developer (JSON)");

    private String label;

    Environment(String label) {
        this.label = label;
    }

    /**
     * Gets env from string.
     *
     * @param s the s
     * @return the env from string
     */
    public static Environment getEnvFromString(String s) {
        return Objects.requireNonNull(getValueMap().entrySet().stream().filter(x -> x.getKey() == s).findFirst().orElse(null)).getValue();
    }

    /**
     * Gets value map.
     *
     * @return the value map
     */
    public static Map<String, Environment> getValueMap() {
        Map<String, Environment> values = new HashMap<>();
        for (Environment value : values()) {
            values.put(value.getLabel(), value);
        }

        return values;
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }
}
