package dk.sdu.swe.domain.models;

import dk.sdu.swe.cross_cutting.exceptions.InvalidNameException;
import dk.sdu.swe.cross_cutting.exceptions.UserCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The type User test.
 */
public class UserTest {
    /**
     * Test user creation.
     */
    @Test
    void testUserCreation() {
        assertDoesNotThrow(() -> {
            new User( "almat20", "alexander@alexander.dk", "Alexander Matzen", "alexander", null);
        });
    }

    /**
     * Test user creation with incorrect name.
     */
    @Test
    void testUserCreationWithIncorrectName() {
        assertThrows(InvalidNameException.class, () -> {
            new User("almat20", "alexander@alexander.dk", "Alexander", "alexander", null);
        });
    }

    /**
     * Test user creation with incorrect email.
     */
    @Test
    void testUserCreationWithIncorrectEmail() {
        assertThrows(UserCreationException.class, () -> {
            new User("almat20", "alexanderalexander.dk", "Alexander Matzen", "alexander", null);
        });
    }

    /**
     * Test user creation with too short username.
     */
    @Test
    void testUserCreationWithTooShortUsername() {
        assertThrows(UserCreationException.class, () -> {
           new User("am", "alexander@alexander.dk", "Alexander Matzen", "alexander", null);
        });
    }
}
