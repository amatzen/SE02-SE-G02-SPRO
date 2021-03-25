package dk.sdu.swe.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dk.sdu.swe.data.IOLoader;
import dk.sdu.swe.models.CompanyAdministrator;
import dk.sdu.swe.models.SystemAdministrator;
import dk.sdu.swe.models.User;
import org.json.JSONArray;
import org.json.JSONObject;

public class AuthController {

    private User currentUser;

    private static AuthController AuthControllerInstance;

    private AuthController() {
    }

    public static synchronized AuthController getInstance() {
        if (AuthControllerInstance == null) {
            AuthControllerInstance = new AuthController();
        }
        return AuthControllerInstance;
    }

    public boolean signIn(String username, String password) throws Exception {

        // SQL
        // SELECT * FROM users WHERE username = ?;

        // Search JSON
        IOLoader ioLoader = new IOLoader("db/users.json");
        JSONArray jsonDB = new JSONArray(ioLoader.readFile());

        JSONObject user = null;

        for (int i = 0; i < jsonDB.length(); i++ ) {
            JSONObject o = jsonDB.getJSONObject(i);
            if(o.getString("username").equals(username)) {
                user = o;
                break;
            }
        }

        if(user == null) {
            return false;
        }

        if(user.getString("password").isEmpty()) {
            return false;
        }

        // Bcrypt validation
        boolean pwOkay = BCrypt.verifyer().verify(password.toCharArray(), user.getString("password").toCharArray()).verified;

        if(!pwOkay) {
            return false;
        }

        this.currentUser = switch (user.getString("permission")) {
            case "SystemAdministrator"  -> new SystemAdministrator(user.getString("username"), user.getString("email"), user.getJSONObject("name").getString("_combined"));
            case "CompanyAdministrator" -> new CompanyAdministrator(user.getString("username"), user.getString("email"), user.getJSONObject("name").getString("_combined"));
            default                     -> new User(user.getString("username"), user.getString("email"), user.getJSONObject("name").getString("_combined"));
        };
        return true;

    }

    public User getUser() {
        return this.currentUser;
    }
}
