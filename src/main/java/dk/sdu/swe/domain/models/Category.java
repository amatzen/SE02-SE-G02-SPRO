package dk.sdu.swe.domain.models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String categoryTitle;

    public Category() {}

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
}
