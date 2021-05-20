package dk.sdu.swe.domain.models;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * The type Category.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(unique = true)
    private String categoryTitle;

    /**
     * Instantiates a new Category.
     */
    public Category() {
    }

    /**
     * Instantiates a new Category.
     *
     * @param categoryTitle the category title
     */
    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    /**
     * Gets category title.
     *
     * @return the category title
     */
    public String getCategoryTitle() {
        return categoryTitle;
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
