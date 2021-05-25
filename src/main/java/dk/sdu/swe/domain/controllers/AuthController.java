package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.controllers.contracts.IAuthController;
import dk.sdu.swe.domain.controllers.contracts.IUserController;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.IUserDAO;
import dk.sdu.swe.persistence.dao.UserDAOImpl;

/**
 * The type Auth controller.
 */
public class AuthController implements IAuthController {

    private static AuthController AuthControllerInstance;
    private User currentUser;

    private IUserDAO userDAO;

    private AuthController() {
        userDAO = UserDAOImpl.getInstance();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized IAuthController getInstance() {
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
    @Override
    public boolean signIn(String username, String password) throws Exception {
        User user = null;

        user = userDAO.getByUsername(username).get();

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
    @Override
    public void logout() {
        this.currentUser = null;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    @Override
    public User getUser() {
        return this.currentUser;
    }
}
