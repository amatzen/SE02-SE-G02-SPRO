package dk.sdu.swe.domain.models;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
