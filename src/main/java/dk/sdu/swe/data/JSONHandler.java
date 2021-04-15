package dk.sdu.swe.data;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sendgrid.Content;
import com.sendgrid.Email;
import dk.sdu.swe.exceptions.UserCreationException;
import dk.sdu.swe.models.User;
import dk.sdu.swe.provider.EmailProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void createUser(User user) throws Exception {
        List<User> users = getUsers();

        User match = users.stream().filter(x -> Objects.equals(x.getUsername(), user.getUsername())).findAny().orElse(null);
        if (match != null) {
            throw new UserCreationException("Username taken.");
        }

        JSONObject json = User.userToJson(user);

        String password = User.createRandomPassword(12);
        String passwordHash = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        json.put("password", passwordHash);
        Content content = new Content("text/plain", "Velkommen til CrMS - et semesterprojekt!\n\nDu kan nu logge ind i CrMS med foelgende brugeroplysninger:\n\nBrugernavn: " + json.getString("username") + "\nAdgangskode: " + password);
        EmailProvider.sendEmail(new Email(json.getString("email")), "Velkommen til CrMS!", content);

    }
}