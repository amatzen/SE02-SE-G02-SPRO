package dk.sdu.swe.cross_cutting.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Environment {
    PROD("Production"),
    LOCAL("Developer (Postgres)"),
    FLATFILE("Developer (JSON)");

    private String label;
    Environment(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Environment getEnvFromString(String s) {
        return Objects.requireNonNull(getValueMap().entrySet().stream().filter(x -> x.getKey() == s).findFirst().orElse(null)).getValue();
    }

    public static Map<String, Environment> getValueMap() {
        Map<String, Environment> values = new HashMap<>();
        for (Environment value : values()) {
            values.put(value.getLabel(), value);
        }

        return values;
    }
}
