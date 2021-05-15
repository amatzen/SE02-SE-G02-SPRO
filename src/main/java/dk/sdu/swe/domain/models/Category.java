package dk.sdu.swe.domain.models;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(unique = true)
    private String categoryTitle;

    public Category() {}

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
