package dk.sdu.swe.domain.models;

import java.util.List;
import java.util.Map;

public class EPGProgramme {

    private String start;
    private String stop;
    private List<String> categories;
    private String epgIdentifier;
    private String epgTitle;
    private Map<String, Boolean> options;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getEpgIdentifier() {
        return epgIdentifier;
    }

    public void setEpgIdentifier(String epgIdentifier) {
        this.epgIdentifier = epgIdentifier;
    }

    public String getEpgTitle() {
        return epgTitle;
    }

    public void setEpgTitle(String epgTitle) {
        this.epgTitle = epgTitle;
    }

    public Map<String, Boolean> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Boolean> options) {
        this.options = options;
    }

}
