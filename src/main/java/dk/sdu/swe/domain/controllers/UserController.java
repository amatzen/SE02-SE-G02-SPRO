package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.models.CompanyAdministrator;
import dk.sdu.swe.domain.models.SystemAdministrator;
import dk.sdu.swe.domain.models.User;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    private static UserController UserControllerInstance;

    private UserController() {
    }

    public static synchronized UserController getInstance() {
        if (UserControllerInstance == null) {
            UserControllerInstance = new UserController();
        }
        return UserControllerInstance;
    }
/*
    public static JSONObject userToJson(User user) {
        JSONObject json = new JSONObject();

        JSONObject name = new JSONObject();
        name.put("firstName", user.getName().getFirstName());
        name.put("lastName", user.getName().getLastName());
        name.put("_combined", user.getName().toString());

        (new HashMap<String, Object>(Map.of(
            "username", user.getUsername(),
            "name", name,
            "email", user.getEmail(),
            "permission", user.getClass().getName().replace("dk.sdu.swe.domain.models.", "")
        ))).forEach((k, v) -> {
            json.put(k, v);
        });

        return json;
    }

    public static User jsonToUser(JSONObject o) throws Exception {
        return switch (o.getString("permission")) {
            case "SystemAdministrator" -> new SystemAdministrator(o.getString("username"), o.getString("email"), o.getJSONObject("name").getString("_combined"), o.getInt("companyId"));
            case "CompanyAdministrator" -> new CompanyAdministrator(o.getString("username"), o.getString("email"), o.getJSONObject("name").getString("_combined"), o.getInt("companyId"));
            default -> new User(o.getString("username"), o.getString("email"), o.getJSONObject("name").getString("_combined"), o.getInt("companyId"));
        };
    }

 */

}