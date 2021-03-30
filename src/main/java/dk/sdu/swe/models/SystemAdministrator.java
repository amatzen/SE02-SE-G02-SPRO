package dk.sdu.swe.models;

public class SystemAdministrator extends User {
    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @throws Exception the exception
     */
    public SystemAdministrator(String username, String email, String name) throws Exception {
        super(username, email, name);
    }
}

