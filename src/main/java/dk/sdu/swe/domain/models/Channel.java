package dk.sdu.swe.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "epg_id")
    private int epgIdentifier;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String logo;

    public Channel(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    public Channel() {
    }

    public int getEpgId() {
        return epgIdentifier;
    }

    public void setEpgId(int epgIdentifier) {
        this.epgIdentifier = epgIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return this.id;
    }
}
