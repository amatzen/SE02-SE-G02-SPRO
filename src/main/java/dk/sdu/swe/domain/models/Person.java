package dk.sdu.swe.domain.models;

import com.google.gson.annotations.SerializedName;
import dk.sdu.swe.cross_cutting.exceptions.PersonCreationException;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Person.
 */
@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @SerializedName("dob")
    @Column(name = "dob")
    private String dateOfBirth;

    private String image;

    @Transient
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;


    @ElementCollection
    @CollectionTable(
        name = "people_contact_details",
        joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")}
    )

    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private final Map<String, String> contactDetails = new HashMap<>();

    @OneToMany(mappedBy = "person")
    private List<Credit> credits;

    /**
     * Instantiates a new Person.
     */
    public Person() {
    }

    /**
     * Instantiates a new Person.
     *
     * @param name        the name
     * @param image       the image
     * @param email       the email
     * @param dateOfBirth the date of birth
     * @throws PersonCreationException the person creation exception
     */
    public Person(String name, String image, String email, ZonedDateTime dateOfBirth) throws PersonCreationException {

        setName(name);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
        setImage(image);

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
    public void setName(String name) throws PersonCreationException {
        if (name.trim().length() < 3 || name.trim().length() > 24) {
            throw new PersonCreationException("Brugernavnet skal være mellem 3 og 24 tegn langt");
        }
        this.name = name;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(ZonedDateTime dateOfBirth) throws PersonCreationException {
        if (dateOfBirth == null) {
            throw new PersonCreationException("Vælg venligst fødselsdato");
        } else {
            setDateOfBirth(dateOfBirth.format(dateTimeFormatter));
        }
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets contact details.
     *
     * @return the contact details
     */
    public JSONObject getContactDetails() {
        return new JSONObject(contactDetails);
    }

    /**
     * Put contact detail.
     *
     * @param key   the key
     * @param value the value
     */
    public void putContactDetail(String key, String value) {
        contactDetails.put(key, value);
    }

    /**
     * Gets contact detail.
     *
     * @param key the key
     * @return the contact detail
     */
    public String getContactDetail(String key) {
        return contactDetails.get(key);
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return getContactDetail("email");
    }

    public void setEmail(String email) throws PersonCreationException {
        if (!email.trim().matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
            throw new PersonCreationException("Ugyldig email");
        }
        putContactDetail("email", email);
    }

    /**
     * Gets credits.
     *
     * @return the credits
     */
    public List<Credit> getCredits() {
        return credits;
    }

    /**
     * Sets credits.
     *
     * @param credits the credits
     */
    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    /**
     * Gets zoned date.
     *
     * @return the zoned date
     */
    public ZonedDateTime getZonedDate() {
        if (dateOfBirth == null) {
            return null;
        } else {
            return ZonedDateTime.parse(dateOfBirth, dateTimeFormatter);
        }
    }
}
