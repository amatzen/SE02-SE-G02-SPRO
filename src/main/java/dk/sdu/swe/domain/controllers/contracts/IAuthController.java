package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.User;

public interface IAuthController {
    boolean signIn(String username, String password) throws Exception;

    void logout();

    User getUser();
}
