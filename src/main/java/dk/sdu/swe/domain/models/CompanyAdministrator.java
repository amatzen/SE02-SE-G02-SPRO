package dk.sdu.swe.domain.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class CompanyAdministrator extends User {
    @Transient
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
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @throws Exception the exception
     */
    public CompanyAdministrator(String username, String email, String name, String password, Company company) throws Exception {
        super(username, email, name, password, company);
    }

    public CompanyAdministrator() { }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> s.equals(permissionKey));
    }
}
