package dk.sdu.swe.domain.models;

import com.google.gson.annotations.Expose;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Arrays;

@Entity(name = "users")
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
    public CompanyAdministrator(int id, String username, String email, String name, int companyId) throws Exception {
        super(id, username, email, name, companyId);
    }

    public CompanyAdministrator() { }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> s.equals(permissionKey));
    }
}
