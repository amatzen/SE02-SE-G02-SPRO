package dk.sdu.swe.domain.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @SerializedName("dob")
    @Column(name = "dob")
    private String dateOfBirth;

    private String image;

    @ElementCollection
    @CollectionTable(
        name = "people_contact_details",
        joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")}
    )

    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> contactDetails = new HashMap<>();

    @OneToMany(mappedBy = "person")
    private List<Credit> credits;

    public Person() {
    }

    public Person(String name, String image, String dateOfBirth) {
        this.name = name;
        this.image = image;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getImage() {
        return image;
    }

    public JSONObject getContactDetails() {
        return new JSONObject(contactDetails);
    }

    public void putContactDetail(String key, String value) {
        contactDetails.put(key, value);
    }

    public String getContactDetail(String key) {
        return contactDetails.get(key);
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }
}
