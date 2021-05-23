package dk.sdu.swe.domain.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.sdu.swe.cross_cutting.exceptions.UserCreationException;
import dk.sdu.swe.persistence.converters.NameConverter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "UserType",
    discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("User")
public class User implements IUser {
    @Transient
    private final String[] permissions = {
        "programmes",
        "programmes.epg",
        "programmes.list",
        "programmes.filter",
        "people"
    };
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    @Column(unique = true)
    private String username;
    @Column
    private String email;
    @Convert(converter = NameConverter.class)
    private Name name;
    @Column
    private String passwordHash;
    @ManyToOne(optional = true)
    private Company company;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @param password the password
     * @param company  the company
     * @throws UserCreationException the user creation exception
     */
    public User(String username, String email, String name, String password, Company company) throws UserCreationException {
        this.company = company;

        // Validate username
        if (username.trim().length() < 3 || username.trim().length() > 24) {
            throw new UserCreationException("Brugernavnet skal være mellem 3 og 24 tegn langt");
        }

        // Validate email
        if (!email.trim().matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
            throw new UserCreationException("Ugyldig email");
        }

        // Validate
        this.username = username.trim();
        this.email = email.trim();
        this.name = new Name(name);

        this.passwordHash = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Name getName() {
        return name;
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
     * Match password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public boolean matchPassword(String password) {
        return BCrypt.verifyer().verify(password.toCharArray(), this.passwordHash.toCharArray()).verified;
    }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> Objects.equals(s, permissionKey));
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", name=" + name +
            ", passwordHash='" + passwordHash + '\'' +
            ", permissions=" + Arrays.toString(permissions) +
            '}';
    }

    /**
     * Gets company.
     *
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets company.
     *
     * @param company the company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Gets programmes.
     *
     * @return the programmes
     */
    public List<Programme> getProgrammes() {
        return null;
    }
}
