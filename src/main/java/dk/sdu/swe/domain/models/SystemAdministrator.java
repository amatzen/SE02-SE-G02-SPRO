package dk.sdu.swe.domain.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@DiscriminatorValue("SystemAdministrator")
public class SystemAdministrator extends User {
    @Transient
    private final String[] permissions = {
        "programmes",
        "programmes.epg",
        "programmes.list",
        "programmes.filter",
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
     * @throws Exception the exception
     */
    public SystemAdministrator(String username, String email, String name, String password, Company company) throws Exception {
        super(username, email, name, password, company);
    }

    public SystemAdministrator() {

    }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> s.equals(permissionKey));
    }
}

