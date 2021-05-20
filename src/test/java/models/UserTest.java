package models;

import static org.junit.jupiter.api.Assertions.*;

import dk.sdu.swe.cross_cutting.exceptions.InvalidNameException;
import dk.sdu.swe.cross_cutting.exceptions.UserCreationException;
import dk.sdu.swe.domain.models.User;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void testUserCreation() {
        assertDoesNotThrow(() -> {
            new User( "almat20", "alexander@alexander.dk", "Alexander Matzen", "alexander", null);
        });
    }

    @Test
    void testUserCreationWithIncorrectName() {
        assertThrows(InvalidNameException.class, () -> {
            new User("almat20", "alexander@alexander.dk", "Alexander", "alexander", null);
        });
    }

    @Test
    void testUserCreationWithIncorrectEmail() {
        assertThrows(UserCreationException.class, () -> {
            new User("almat20", "alexanderalexander.dk", "Alexander Matzen", "alexander", null);
        });
    }

    @Test
    void testUserCreationWithTooShortUsername() {
        assertThrows(UserCreationException.class, () -> {
           new User("am", "alexander@alexander.dk", "Alexander Matzen", "alexander", null);
        });
    }
}
