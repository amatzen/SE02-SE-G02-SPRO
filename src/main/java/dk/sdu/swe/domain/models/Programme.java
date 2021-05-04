package dk.sdu.swe.domain.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programme")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String channel;

    private int prodYear;
    private String category;

    @Transient
    private List<EPGProgramme> epgDates;

    public Programme() {
    }

    public Programme(String title, String channel, int prodYear, String category) {
        this.title = title;
        this.channel = channel;
        this.prodYear = prodYear;
        this.category = category;
    }

    public Programme() {
    }

    public String getTitle() {
        return title;
    }

    public String getChannel() {
        return channel;
    }

    public int getProdYear() {
        return prodYear;
    }

    public String getCategory() {
        return category;
    }

    public List<EPGProgramme> getEpgDates() {
        return epgDates;
    }

}
