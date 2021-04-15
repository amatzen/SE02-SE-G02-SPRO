package dk.sdu.swe.models;

import java.util.List;
import java.util.Map;

public class EPGProgramme {

    private String start;
    private String stop;
    private List<String> categories;
    private String epgIdentifier;
    private String epgTitle;
    private Map<String, Boolean> options;


    public String getEpgTitle() {
        return epgTitle;
    }

}
