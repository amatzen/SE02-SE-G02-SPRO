package dk.sdu.swe.data;

import dk.sdu.swe.models.CompanyAdministrator;
import dk.sdu.swe.models.SystemAdministrator;
import dk.sdu.swe.models.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler implements PersistenceContract {

    private static JSONHandler JSONHandlerInstance;

    private JSONHandler() {
    }

    public static synchronized JSONHandler getInstance() {
        if (JSONHandlerInstance == null) {
            JSONHandlerInstance = new JSONHandler();
        }
        return JSONHandlerInstance;
    }

    private IOLoader getUserIOLoader() {
        return new IOLoader("db/users.json");
    }

    @Override
    public List<User> getUsers() throws Exception {
        IOLoader ioLoader = getUserIOLoader();
        JSONArray jsonArray = new JSONArray(ioLoader.readFile());

        ArrayList<User> userList = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject o = jsonArray.getJSONObject(i);
            userList.add(User.jsonToUser(o));
        }

        return userList;
    }

    @Override
    public User getUser(int id) throws Exception {
        List<User> users = getUsers();
        return users.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    @Override
    public void createUser(User user) {

    }
}