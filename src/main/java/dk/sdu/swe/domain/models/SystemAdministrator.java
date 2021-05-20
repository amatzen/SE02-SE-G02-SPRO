package dk.sdu.swe.domain.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Arrays;

/**
 * The type System administrator.
 */
@Entity
@DiscriminatorValue("SystemAdministrator")
public class SystemAdministrator extends User {
    @Transient
    private final String[] permissions = {
        "programmes",
        "programmes.epg",
        "programmes.list",
        "programmes.list.all",
        "programmes.filter",
        "programmes.change.no_review",
        "companies",
        "companies.list",
        "companies.list.all",
        "companies.add",
        "companies.user.promote",
        "people",
        "admin",
        "admin.reviews",
        "admin.export",
        "admin.credit_groups"
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
    public SystemAdministrator(String username, String email, String name, String password, Company company) throws Exception {
        super(username, email, name, password, company);
    }

    /**
     * Instantiates a new System administrator.
     */
    public SystemAdministrator() {

    }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> s.equals(permissionKey));
    }
}

