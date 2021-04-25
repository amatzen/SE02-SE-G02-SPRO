package dk.sdu.swe.domain.models;

import dk.sdu.swe.data.FacadeDB;

import java.util.List;

public class Credit {

    private int id;
    private int programme;
    private String[] epg;
    private int person;

    public Credit(int id, int programme, String[] epg, int person) {
        this.id = id;
        this.programme = programme;
        this.epg = epg;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgramme() {
        return programme;
    }

    public void setProgramme(int programme) {
        this.programme = programme;
    }

    public String[] getEpg() {
        return epg;
    }

    public void setEpg(String[] epg) {
        this.epg = epg;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public static List<Credit> getAll() throws Exception {
        return FacadeDB.getInstance().getCredits();
    }
}
