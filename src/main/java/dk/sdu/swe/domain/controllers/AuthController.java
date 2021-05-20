package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.persistence.dao.UserDAOImpl;

/**
 * The type Auth controller.
 */
public class AuthController {

    private static AuthController AuthControllerInstance;
    private User currentUser;

    private AuthController() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized AuthController getInstance() {
        if (AuthControllerInstance == null) {
            AuthControllerInstance = new AuthController();
        }
        return AuthControllerInstance;
    }

    /**
     * Sign in boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     * @throws Exception the exception
     */
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

    /**
     * Logout.
     */
    public void logout() {
        this.currentUser = null;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return this.currentUser;
    }
}
