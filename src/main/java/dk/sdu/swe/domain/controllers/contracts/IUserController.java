package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.cross_cutting.exceptions.UserCreationException;

import java.util.List;

public interface IUserController {
    User createUser(String username, String email, String name, Company company) throws UserCreationException;

    void delete(User user);

    List<User> getAll();
}
