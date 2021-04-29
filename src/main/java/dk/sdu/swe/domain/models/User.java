package dk.sdu.swe.domain.models;

import dk.sdu.swe.exceptions.UserCreationException;
import dk.sdu.swe.data.FacadeDB;
import org.hibernate.annotations.Type;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.*;

/**
 * The type User.
 */ 
@MappedSuperclass
@Entity(name = "users")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "UserType",
    discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("User")
public class User implements IUser {
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String email;
    private int companyId;

    @Column()
    @Type(type = "string")
    private Name name;

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
     * @param id       the user id
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @param companyId
     * @throws Exception the exception
     */
    public User(int id, String username, String email, String name, int companyId) throws Exception {

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

        this.id = id;
        this.companyId = companyId;
    }

    public User() {}

    public static User get(int id) throws Exception {
        return FacadeDB.getInstance().getUser(id);
    }

    public static List<User> getAll() throws Exception {
        return FacadeDB.getInstance().getUsers();
    }

    public static void create(User user) throws Exception {
        FacadeDB.getInstance().createUser(user);
    }

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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> Objects.equals(s, permissionKey));
    }
}