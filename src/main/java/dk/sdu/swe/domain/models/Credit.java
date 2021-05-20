package dk.sdu.swe.domain.models;

import org.json.JSONObject;

import javax.persistence.*;

/**
 * The type Credit.
 */
@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private CreditRole role;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "programme_id", referencedColumnName = "id")
    private Programme programme;

    /**
     * Instantiates a new Credit.
     *
     * @param person     the person
     * @param creditRole the credit role
     */
    public Credit(Person person, CreditRole creditRole) {
        this.person = person;
        this.role = creditRole;
    }

    /**
     * Instantiates a new Credit.
     */
    public Credit() {
    }

    @Override
    public String toString() {
        return "Credit{" +
            "id=" + id +
            ", role=" + role +
            ", person=" + person +
            ", programme=" + programme +
            '}';
    }

    /**
     * To json json object.
     *
     * @return the json object
     */
    public JSONObject toJson() {
        JSONObject a = new JSONObject();

        a.put("id", id);
        a.put("role", new JSONObject().put("id", getRole().getId()).put("title", getRole().getTitle()));
        a.put("person", new JSONObject().put("id", getPerson().getId()).put("name", getPerson().getName()));
        a.put("programme", programme.getId());

        return a;
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets person.
     *
     * @param person the person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Gets programme.
     *
     * @return the programme
     */
    public Programme getProgramme() {
        return programme;
    }

    /**
     * Sets programme.
     *
     * @param programme the programme
     */
    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public CreditRole getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(CreditRole role) {
        this.role = role;
    }
}
