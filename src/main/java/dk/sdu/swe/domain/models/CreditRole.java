package dk.sdu.swe.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The type Credit role.
 */
@Entity
public class CreditRole {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String title;

    /**
     * Instantiates a new Credit role.
     */
    public CreditRole() {
    }

    /**
     * Instantiates a new Credit role.
     *
     * @param title the title
     */
    public CreditRole(String title) {
        this.title = title;
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
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
