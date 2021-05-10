package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.User;

import java.util.Optional;

public interface IUserDAO extends IDAO<User> {

    public Optional<User> getByUsername(String username);

}
