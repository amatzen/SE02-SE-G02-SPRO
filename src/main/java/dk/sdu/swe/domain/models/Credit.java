package dk.sdu.swe.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_custom_role", nullable = false)
    private boolean customRole;

    @Column(name = "custom_role_text", nullable = true)
    private String customRoleText;

    @ManyToOne(optional = true)
    private CreditRole role;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "programme_id", referencedColumnName = "id")
    private Programme programme;

    public Credit(Person person) {
        this.person = person;
    }

    public Credit() {}

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
