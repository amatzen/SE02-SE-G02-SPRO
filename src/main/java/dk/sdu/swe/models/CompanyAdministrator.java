package dk.sdu.swe.models;

import java.util.Arrays;

public class CompanyAdministrator extends User implements IUser {
    private final String[] permissions = {
        "programmes",
        "programmes.epg",
        "programmes.list",
        "programmes.filter",
        "companies",
        "companies.own",
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
    public CompanyAdministrator(int id, String username, String email, String name) throws Exception {
        super(id, username, email, name);
    }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> s.equals(permissionKey));
    }
}
