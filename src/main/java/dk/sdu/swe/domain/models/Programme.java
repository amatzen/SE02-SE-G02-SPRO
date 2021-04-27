package dk.sdu.swe.domain.models;

import dk.sdu.swe.data.FacadeDB;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Programme {

    private int id;
    private String title;
    private String channel;
    private int prodYear;
    private String category;
    private List<EPGProgramme> epgDates;
    private Map<String, Person> people;

    public Programme(int id, String title, String channel, int prodYear, String category) {
        this.id = id;
        this.title = title;
        this.channel = channel;
        this.prodYear = prodYear;
        this.category = category;
    }

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getProdYear() {
        return prodYear;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public static List<Programme> getAll() throws Exception {
        return FacadeDB.getInstance().getProgrammes();
    }
}
