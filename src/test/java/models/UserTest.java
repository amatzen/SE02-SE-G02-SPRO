package models;

import static org.junit.jupiter.api.Assertions.*;

import dk.sdu.swe.exceptions.InvalidNameException;
import dk.sdu.swe.exceptions.UserCreationException;
import dk.sdu.swe.models.User;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void testUserCreation() {
        assertDoesNotThrow(() -> {
            new User("almat20", "alexander@alexander.dk", "Alexander Matzen");
        });
    }

    @Test
    void testUserCreationWithIncorrectName() {
        assertThrows(InvalidNameException.class, () -> {
            new User("almat20", "alexander@alexander.dk", "Alexander");
        });
    }

    @Test
    void testUserCreationWithIncorrectEmail() {
        assertThrows(UserCreationException.class, () -> {
            new User("almat20", "alexanderalexander.dk", "Alexander Matzen");
        });
    }

    @Test
    void testUserCreationWithTooShortUsername() {
        assertThrows(UserCreationException.class, () -> {
           new User("am", "alexander@alexander.dk", "Alexander Matzen");
        });
    }
}
