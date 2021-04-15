package dk.sdu.swe.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.sdu.swe.data.JSONHandler;
import dk.sdu.swe.data.PersistenceContract;
import dk.sdu.swe.exceptions.UserCreationException;
import org.json.JSONObject;

import java.util.*;

/**
 * The type User.
 */
public class User implements IUser {
    private int id;
    private String username;
    private String email;
    private Name name;

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
     * @throws Exception the exception
     */
    public User(int id, String username, String email, String name) throws Exception {

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

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> Objects.equals(s, permissionKey));
    }


    public static User get(int id) throws Exception {
        return JSONHandler.getInstance().getUser(id);
    }

    public static List<User> getAll() throws Exception {
        return JSONHandler.getInstance().getUsers();
    }

    public static void create(User user) throws Exception {
        JSONHandler.getInstance().createUser(user);
    }


    public static User jsonToUser(JSONObject o) throws Exception {
        return switch (o.getString("permission")) {
            case "SystemAdministrator"  ->  new SystemAdministrator(o.getInt("id"), o.getString("username"), o.getString("email"), o.getJSONObject("name").getString("_combined"));
            case "CompanyAdministrator" -> new CompanyAdministrator(o.getInt("id"), o.getString("username"), o.getString("email"), o.getJSONObject("name").getString("_combined"));
            default                     ->                 new User(o.getInt("id"), o.getString("username"), o.getString("email"), o.getJSONObject("name").getString("_combined"));
        };
    }
        JSONObject name = new JSONObject();
    public static String createRandomPassword(int length) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#";
        StringBuilder passwordBuilder = new StringBuilder();

        Random randomizer = new Random();
        while(passwordBuilder.length() < length) {
            passwordBuilder.append(allowedCharacters.charAt((int) (randomizer.nextFloat() * allowedCharacters.length())));
        }

        return passwordBuilder.toString();
    }
}

