package dk.sdu.swe.domain.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.sdu.swe.data.converters.NameConverter;
import dk.sdu.swe.exceptions.UserCreationException;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements IUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(unique = true)
    private String username;

    @Column
    private String email;

    @Convert(converter = NameConverter.class)
    private Name name;

    @Column
    private String passwordHash;

    @Transient
    private final String[] permissions = {
        "programmes",
        "programmes.epg",
        "programmes.list",
        "programmes.filter",
        "people"
    };

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @param password the password
     * @throws Exception the exception
     */
    public User(String username, String email, String name, String password) throws Exception {

        // Validate username
        if (username.trim().length() < 3 || username.trim().length() > 24) {
            throw new UserCreationException("Username must be between 3 and 24 characters long");
        }

        // Validate email
        if (!email.trim().matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
            throw new UserCreationException("Email invalid.");
        }

        // Validate
        this.username = username.trim();
        this.email = email.trim();
        this.name = new Name(name);

        this.passwordHash = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    public int getId() {
        return id;
    }

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
}
