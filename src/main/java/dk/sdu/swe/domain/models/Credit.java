package dk.sdu.swe.domain.models;

import org.json.JSONObject;

import javax.persistence.*;

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

    public Credit(Person person, CreditRole creditRole) {
        this.person = person;
        this.role = creditRole;
    }

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

    public JSONObject toJson() {
        JSONObject a = new JSONObject();

        a.put("id", id);
        a.put("role", new JSONObject().put("id", getRole().getId()).put("title", getRole().getTitle()));
        a.put("person", new JSONObject().put("id", getPerson().getId()).put("name", getPerson().getName()));
        a.put("programme", programme.getId());

        return a;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public CreditRole getRole() {
        return role;
    }

    public void setRole(CreditRole role) {
        this.role = role;
    }
}
