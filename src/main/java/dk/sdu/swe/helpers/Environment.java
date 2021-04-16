package dk.sdu.swe.helpers;

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
}