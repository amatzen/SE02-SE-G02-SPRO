package dk.sdu.swe.data;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sendgrid.Content;
import com.sendgrid.Email;
import dk.sdu.swe.domain.models.EPGProgramme;
import dk.sdu.swe.exceptions.UserCreationException;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.provider.EmailProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
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

    private IOHandler getUserIOLoader() {
        return new IOHandler("db/users.json");
    }

    private IOHandler getProgrammeIOLoader() {
        return new IOHandler("db/programmes.json");
    }

    @Override
    public List<User> getUsers() throws Exception {
        IOHandler ioHandler = getUserIOLoader();
        JSONArray jsonArray = new JSONArray(ioHandler.readFile());

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
        Content content = new Content(
            "text/plain",
            "Velkommen til CrMS - et semesterprojekt!\n\n" +
            "Du kan nu logge ind i CrMS med foelgende brugeroplysninger:\n\n" +
            "Brugernavn: " + json.getString("username") + "\nAdgangskode: " + password);
        EmailProvider.sendEmail(new Email(json.getString("email")), "Velkommen til CrMS!", content);

        IOHandler ioHandler = getUserIOLoader();
        ioHandler.jsonAppendToArray(json);
    }

    @Override
    public void updateUser(int id, User user) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(int id) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Person> getPeople() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Person getPerson() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createPerson(Person person) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updatePerson(int id, Person person) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deletePerson(int id) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Programme> getProgrammes() throws Exception {
        IOHandler ioHandler = getProgrammeIOLoader();
        Type listType = new TypeToken<List<Programme>>(){}.getType();
        return new Gson().fromJson(ioHandler.readFile(), listType);
    }

    @Override
    public Programme getProgramme(int id) throws Exception {
        return getProgrammes().stream().filter(programme -> programme.getId() == id).findAny().orElse(null);
    }

    @Override
    public void createProgramme(Programme programme) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateProgramme(int id, Programme programme) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteProgramme(int id) throws Exception {
        throw new UnsupportedOperationException();
    }
}
