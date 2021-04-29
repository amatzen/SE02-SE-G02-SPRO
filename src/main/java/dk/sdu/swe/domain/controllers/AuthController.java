package dk.sdu.swe.domain.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

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
        System.out.println(this.currentUser);
        System.out.println(username + ":" + password);
        User user = null;

        Session session = DB.openSession();
        user = session.byNaturalId(User.class)
            .using("username", username)
            .load();
        session.close();

        if (user == null) {
            return false;
        }

        System.out.println(user.toString());

        if(!user.matchPassword(password)) {
            return false;
        }

        this.currentUser = user;
        System.out.println(this.currentUser);
        return true;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getUser() {
        return this.currentUser;
    }
}
