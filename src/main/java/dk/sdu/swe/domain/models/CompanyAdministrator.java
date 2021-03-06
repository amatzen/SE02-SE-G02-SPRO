package dk.sdu.swe.domain.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Arrays;

/**
 * The type Company administrator.
 */
@Entity
@DiscriminatorValue("CompanyAdministrator")
public class CompanyAdministrator extends User {
    @Transient
    private final String[] permissions = {
        "programmes",
        "programmes.epg",
        "programmes.list",
        "programmes.filter",
        "companies",
        "companies.own",
        "companies.user.promote",
        "people",
        "user.promote"
    };

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @param password the password
     * @param company  the company
     * @throws Exception the exception
     */
    public CompanyAdministrator(String username, String email, String name, String password, Company company) throws Exception {
        super(username, email, name, password, company);
    }

    /**
     * Instantiates a new Company administrator.
     */
    public CompanyAdministrator() {
    }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> s.equals(permissionKey));
    }
}
