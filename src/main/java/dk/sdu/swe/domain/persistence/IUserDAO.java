package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.User;

import java.util.Optional;

/**
 * The interface User dao.
 */
public interface IUserDAO extends IDAO<User> {

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    public Optional<User> getByUsername(String username);

    /**
     * Promote user.
     *
     * @param user the user
     * @param type the type
     */
    public void promoteUser(User user, String type);
}
