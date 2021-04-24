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

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Boolean> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Boolean> options) {
        this.options = options;
    }

}
