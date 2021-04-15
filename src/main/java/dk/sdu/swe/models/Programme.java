package dk.sdu.swe.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Programme {

    private int id;
    private String title;
    private List<EPGProgramme> epgDates;
    private Map<String, Person> map = new HashMap<String, Person>();

    public String getTitle() {
        return title;
    }

    public EPGProgramme getEPGDates() {
        return (EPGProgramme) epgDates;
    }

    // Alexander laver den her
    public Object getPeople() {
      return "";
    }

}
