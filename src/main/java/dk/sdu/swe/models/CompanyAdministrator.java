package dk.sdu.swe.models;

public class CompanyAdministrator extends User {
    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @throws Exception the exception
     */
    public CompanyAdministrator(String username, String email, String name) throws Exception {
        super(username, email, name);
    }
}
