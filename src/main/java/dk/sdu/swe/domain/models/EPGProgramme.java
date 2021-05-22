package dk.sdu.swe.domain.models;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * The type Epg programme.
 */
@Entity
@Table(name = "programme_epg")
public class EPGProgramme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String start;
    private String stop;
    private String epgIdentifier;

    private Long epgChannelId;

    @Column
    private String title;

    @Transient
    private List<String> categories;

    @Transient
    private Map<String, Boolean> options;

    /**
     * Instantiates a new Epg programme.
     *
     * @param epgChannelId  the epg channel id
     * @param start         the start
     * @param stop          the stop
     * @param categories    the categories
     * @param epgIdentifier the epg identifier
     * @param title         the title
     * @param options       the options
     */
    public EPGProgramme(Long epgChannelId, Instant start, Instant stop, List<String> categories, String epgIdentifier, String title, Map<String, Boolean> options) {
        this.epgChannelId = epgChannelId;
        this.start = start.toString();
        this.stop = stop.toString();
        this.categories = categories;
        this.epgIdentifier = epgIdentifier;
        this.title = title;
        this.options = options;
    }

    /**
     * Instantiates a new Epg programme.
     */
    public EPGProgramme() {

    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * Gets stop.
     *
     * @return the stop
     */
    public String getStop() {
        return stop;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Gets epg identifier.
     *
     * @return the epg identifier
     */
    public String getEpgIdentifier() {
        return epgIdentifier;
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
     * Gets options.
     *
     * @return the options
     */
    public Map<String, Boolean> getOptions() {
        return options;
    }

}
