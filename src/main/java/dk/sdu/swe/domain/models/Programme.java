package dk.sdu.swe.domain.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "programme")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private int prodYear;

    @Transient
    private List<EPGProgramme> epgDates;

    public Programme(String title, Channel channel, int prodYear, List<Category> categories) {
        this.title = title;
        this.channel = channel;
        this.prodYear = prodYear;
        this.categories = categories;
    }

    public Programme() {
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


    @OneToMany
    private Collection<Category> categories;

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    @ManyToOne(optional = true)
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
