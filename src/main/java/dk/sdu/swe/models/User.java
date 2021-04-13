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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }
}

