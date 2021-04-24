package models;

import static org.junit.jupiter.api.Assertions.*;

import dk.sdu.swe.data.JSONHandler;
import dk.sdu.swe.domain.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserPersistenceTest {
    @Test
    void testGetAllUsers() {
        JSONHandler jsonHandler = JSONHandler.getInstance();
        assertDoesNotThrow(() -> {
            List<User> users = jsonHandler.getUsers();
            users.forEach(u -> System.out.println(u.getUsername()));
        });



    }
}
