package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.User;

import java.util.Optional;

public interface IUserDAO extends IDAO<User> {

    public Optional<User> getByUsername(String username);

    public void promoteUser(User user, String type);
}
