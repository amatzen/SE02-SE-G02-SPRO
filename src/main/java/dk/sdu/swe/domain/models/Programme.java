package dk.sdu.swe.domain.models;

import dk.sdu.swe.persistence.dao.CreditDAOImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Programme.
 */
@Entity
@Table(name = "programme")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int prodYear;

    @OneToMany
    @JoinTable(name = "programme_epg_entries")
    private List<EPGProgramme> epgProgrammes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    @ManyToOne
    private Channel channel;

    @ManyToOne
    private Company company;

    /**
     * Instantiates a new Programme.
     *
     * @param title      the title
     * @param channel    the channel
     * @param prodYear   the prod year
     * @param categories the categories
     * @param company    the company
     */
    public Programme(String title, Channel channel, int prodYear, Set<Category> categories, Company company) {
        this.title = title;
        this.channel = channel;
        this.prodYear = prodYear;
        this.categories = categories;
        this.company = company;
    }

    /**
     * Instantiates a new Programme.
     */
    public Programme() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets credits.
     *
     * @return the credits
     */
    public List<Credit> getCredits() {
        return CreditDAOImpl.getInstance().getAll()
            .stream()
            .filter(x -> Objects.equals(x.getProgramme().getId(), this.getId()))
            .collect(Collectors.toList());
    }

    /**
     * Gets credits json.
     *
     * @return the credits json
     */
    public JSONArray getCreditsJson() {
        JSONArray a = new JSONArray();
        getCredits().forEach(x -> a.put(x.toJson()));
        return a;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets prod year.
     *
     * @return the prod year
     */
    public int getProdYear() {
        return prodYear;
    }

    /**
     * Sets prod year.
     *
     * @param prodYear the prod year
     */
    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }

    /**
     * Gets epg programmes.
     *
     * @return the epg programmes
     */
    public List<EPGProgramme> getEpgProgrammes() {
        return epgProgrammes;
    }

    /**
     * Sets epg programmes.
     *
     * @param epgProgrammes the epg programmes
     */
    public void setEpgProgrammes(List<EPGProgramme> epgProgrammes) {
        this.epgProgrammes = epgProgrammes;
    }

    /**
     * Add epg programmme.
     *
     * @param epgProgramme the epg programme
     */
    public void addEpgProgrammme(EPGProgramme epgProgramme) {
        this.epgProgrammes.add(epgProgramme);
    }

    /**
     * Remove epg programme.
     *
     * @param epgProgramme the epg programme
     */
    public void removeEpgProgramme(EPGProgramme epgProgramme) {
        this.epgProgrammes.remove(epgProgramme);
    }

    /**
     * Gets channel.
     *
     * @return the channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Sets channel.
     *
     * @param channel the channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Sets categories.
     *
     * @param categories the categories
     */
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    /**
     * To json json object.
     *
     * @return the json object
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("prodYear", this.prodYear);
        //json.put("epgDates", Objects.requireNonNullElse(this.epgDates, new ArrayList<>()).toArray());
        json.put("categories", this.categories.stream().map(Category::getId).toArray());
        json.put("channel", this.channel.getId());
        json.put("credits", this.getCreditsJson());
        return json;
    }

    public Programme clone() {
        try {
            return (Programme) super.clone();
        } catch (CloneNotSupportedException e) {
            Programme x = new Programme(this.title, this.channel, this.prodYear, this.categories, this.company);
            return x;
        }
    }

    /**
     * Gets company.
     *
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets company.
     *
     * @param company the company
     */
    public void setCompany(Company company) {
        this.company = company;
    }
}
