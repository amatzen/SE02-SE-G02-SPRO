package dk.sdu.swe.models;

import java.util.List;
import java.util.Map;

public class Programme {

    private int id;
    private String title;
    private List<EPGProgramme> epgDates;
    private Map<String, Person> people;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<EPGProgramme> getEpgDates() {
        return epgDates;
    }

    public void setEpgDates(List<EPGProgramme> epgDates) {
        this.epgDates = epgDates;
    }

    public Map<String, Person> getPeople() {
        return people;
    }

    public void setPeople(Map<String, Person> people) {
        this.people = people;
    }

}
