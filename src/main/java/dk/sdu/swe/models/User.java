package dk.sdu.swe.models;

import dk.sdu.swe.exceptions.InvalidNameException;
import dk.sdu.swe.exceptions.UserCreationException;

/**
 * The type User.
 */
public class User {
    private String username;
    private String email;
    private Name name;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @throws Exception the exception
     */
    public User(String username, String email, String name) throws Exception {

        // Validate username
        if(username.trim().length() < 3 || username.trim().length() > 24) {
            throw new UserCreationException("Username must be between 3 and 24 characters long");
        }

        // Validate email
        if(!email.trim().matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
            throw new UserCreationException("Email invalid.");
        }

        // Validate
        this.username = username.trim();
        this.email = email.trim();
        this.name = new Name(name);
    }



}

/**
 * Name
 */
class Name {
    /**
     * The First name.
     */
    protected String firstName;
    /**
     * The Last name.
     */
    protected String lastName;

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }

    /**
     * Instantiates a new Name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Instantiates a new Name.
     *
     * @param name the name
     * @throws Exception the exception
     */
    public Name(String name) throws Exception {
        if(name.trim().split(" ").length != 2) {
            throw new InvalidNameException("Name must contain both first and last name. No middle names allowed!");
        }

        this.firstName = name.trim().split(" ")[0];
        this.lastName = name.trim().split(" ")[1];
    }
}
