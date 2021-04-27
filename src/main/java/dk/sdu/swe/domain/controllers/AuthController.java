package dk.sdu.swe.domain.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.sdu.swe.data.IOHandler;
import dk.sdu.swe.domain.models.User;
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
        // Search JSON
        IOHandler ioHandler = new IOHandler("db/users.json");
        JSONArray jsonDB = new JSONArray(ioHandler.readFile());

        JSONObject user = null;

        for (int i = 0; i < jsonDB.length(); i++) {
            JSONObject o = jsonDB.getJSONObject(i);
            if (o.getString("username").equals(username)) {
                user = o;
                break;
            }
        }

        if (user == null) {
            return false;
        }

        if (user.getString("password").isEmpty()) {
            return false;
        }

        // Bcrypt validation
        boolean pwOkay = BCrypt.verifyer().verify(password.toCharArray(), user.getString("password").toCharArray()).verified;

        if (!pwOkay) {
            return false;
        }

        this.currentUser = UserController.jsonToUser(user);
        return true;

    }

    public void logout() {
        this.currentUser = null;
    }

    public User getUser() {
        return this.currentUser;
    }
}
