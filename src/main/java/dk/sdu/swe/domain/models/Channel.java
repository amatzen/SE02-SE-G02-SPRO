package dk.sdu.swe.domain.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * The type Channel.
 */
@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(name = "epg_id")
    private Long epgIdentifier;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String logo;

    /**
     * Instantiates a new Channel.
     *
     * @param name the name
     * @param logo the logo
     */
    public Channel(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    /**
     * Instantiates a new Channel.
     */
    public Channel() {
    }

    /**
     * Gets epg id.
     *
     * @return the epg id
     */
    public Long getEpgId() {
        return epgIdentifier;
    }

    /**
     * Sets epg id.
     *
     * @param epgIdentifier the epg identifier
     */
    public void setEpgId(Long epgIdentifier) {
        this.epgIdentifier = epgIdentifier;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets logo.
     *
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets logo.
     *
     * @param logo the logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }
}
