package models;

import static org.junit.jupiter.api.Assertions.*;

import dk.sdu.swe.exceptions.InvalidNameException;
import dk.sdu.swe.exceptions.UserCreationException;
import dk.sdu.swe.domain.models.User;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void testUserCreation() {
        assertDoesNotThrow(() -> {
            new User(1, "almat20", "alexander@alexander.dk", "Alexander Matzen", );
        });
    }

    @Test
    void testUserCreationWithIncorrectName() {
        assertThrows(InvalidNameException.class, () -> {
            new User(2, "almat20", "alexander@alexander.dk", "Alexander", );
        });
    }

    @Test
    void testUserCreationWithIncorrectEmail() {
        assertThrows(UserCreationException.class, () -> {
            new User(3, "almat20", "alexanderalexander.dk", "Alexander Matzen", );
        });
    }

    @Test
    void testUserCreationWithTooShortUsername() {
        assertThrows(UserCreationException.class, () -> {
           new User(4, "am", "alexander@alexander.dk", "Alexander Matzen", );
        });
    }
}
