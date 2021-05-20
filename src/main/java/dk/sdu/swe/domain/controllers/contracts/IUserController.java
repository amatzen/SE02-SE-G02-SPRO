package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.cross_cutting.exceptions.UserCreationException;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.User;

import java.util.List;

/**
 * The interface User controller.
 */
public interface IUserController {
    /**
     * Create user user.
     *
     * @param username the username
     * @param email    the email
     * @param name     the name
     * @param company  the company
     * @return the user
     * @throws UserCreationException the user creation exception
     */
    User createUser(String username, String email, String name, Company company) throws UserCreationException;

    /**
     * Delete.
     *
     * @param user the user
     */
    void delete(User user);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<User> getAll();
}
