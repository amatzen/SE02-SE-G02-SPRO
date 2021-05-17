package dk.sdu.swe.domain.models;

import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    @ManyToOne
    private Channel channel;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "programme")
    private List<Credit> credits;

    public Programme(String title, Channel channel, int prodYear, Set<Category> categories, Company company) {
        this.title = title;
        this.channel = channel;
        this.prodYear = prodYear;
        this.categories = categories;
        this.company = company;
    }

    public Programme() {
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("prodYear", this.prodYear);
        json.put("epgDates", this.epgDates.toArray());
        json.put("categories", this.categories.stream().map(Category::getId).toArray());
        json.put("channel", this.channel.getId());
        json.put("credits", this.credits.toArray());
        return json;
    }

    public Programme clone() {
        try {
            return (Programme) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Programme(this.title, this.channel, this.prodYear, this.categories, this.company);
        }
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
