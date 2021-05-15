package dk.sdu.swe.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CreditRole {
    String title;

    @Id
    @GeneratedValue
    private Integer id;

    public CreditRole() {}

    public CreditRole(String title) {
        this.title = title;
    }
}
