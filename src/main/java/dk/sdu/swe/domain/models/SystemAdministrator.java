package dk.sdu.swe.domain.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Arrays;

@Entity
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
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @throws Exception the exception
     */
    public SystemAdministrator(String username, String email, String name, String password) throws Exception {
        super(username, email, name, password);
    }

    public SystemAdministrator() {

    }

    @Override
    public boolean hasPermission(String permissionKey) {
        return Arrays.stream(this.permissions).anyMatch(s -> s.equals(permissionKey));
    }
}

