package dk.sdu.swe.domain.models;

import java.util.List;
import java.util.Map;

public class EPGProgramme {

    private int start;
    private int stop;
    private List<String> categories;
    private String epgIdentifier;
    private String title;
    private Map<String, Boolean> options;

    public EPGProgramme(int start, int stop, List<String> categories, String epgIdentifier, String title, Map<String, Boolean> options) {
        this.start = start;
        this.stop = stop;
        this.categories = categories;
        this.epgIdentifier = epgIdentifier;
        this.title = title;
        this.options = options;
    }

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getEpgIdentifier() {
        return epgIdentifier;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, Boolean> getOptions() {
        return options;
    }

}
