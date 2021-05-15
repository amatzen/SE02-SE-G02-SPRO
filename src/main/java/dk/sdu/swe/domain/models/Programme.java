package dk.sdu.swe.domain.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programme")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int prodYear;

    @Transient
    private List<EPGProgramme> epgDates;

    @ManyToMany
    private List<Category> categories;

    @ManyToOne(optional = true)
    private Channel channel;

    @OneToMany(mappedBy = "programme")
    private List<Credit> credits;

    public Programme(String title, Channel channel, int prodYear, List<Category> categories) {
        this.title = title;
        this.channel = channel;
        this.prodYear = prodYear;
        this.categories = categories;
    }

    public Programme() {
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public String getTitle() {
        return title;
    }

    public int getProdYear() {
        return prodYear;
    }

    public List<EPGProgramme> getEpgDates() {
        return epgDates;
    }

    public Channel getChannel() {
        return channel;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }
}
