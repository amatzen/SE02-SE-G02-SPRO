package dk.sdu.swe.domain.models;

import org.joda.time.Instant;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "programme_epg_entries")
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

    public EPGProgramme(Long epgChannelId, Instant start, Instant stop, List<String> categories, String epgIdentifier, String title, Map<String, Boolean> options) {
        this.epgChannelId = epgChannelId;
        this.start = start.toString();
        this.stop = stop.toString();
        this.categories = categories;
        this.epgIdentifier = epgIdentifier;
        this.title = title;
        this.options = options;
    }

    public String getStart() {
        return start;
    }

    public String getStop() {
        return stop;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getEpgIdentifier() {
        return epgIdentifier;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, Boolean> getOptions() {
        return options;
    }

}
