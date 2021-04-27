package dk.sdu.swe.domain.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Arrays;

@Entity(name = "users")
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
        "companies.add",
        "people",
        "admin",
        "admin.reviews",
        "admin.export",
        "admin.credit_groups"
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
    public SystemAdministrator(int id, String username, String email, String name, int companyId) throws Exception {
        super(id, username, email, name, companyId);
    }

    public SystemAdministrator() {

    }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> s.equals(permissionKey));
    }
}

