package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.persistence.dao.UserDAOImpl;

public class AuthController {

    private static AuthController AuthControllerInstance;
    private User currentUser;

    private AuthController() {
    }

    public static synchronized AuthController getInstance() {
        if (AuthControllerInstance == null) {
            AuthControllerInstance = new AuthController();
        }
        return AuthControllerInstance;
    }

    public boolean signIn(String username, String password) throws Exception {
        User user = null;

        user = UserDAOImpl.getInstance().getByUsername(username).get();

        if (user == null) {
            return false;
        }

        if (!user.matchPassword(password)) {
            return false;
        }

        this.currentUser = user;
        return true;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getUser() {
        return this.currentUser;
    }
}
