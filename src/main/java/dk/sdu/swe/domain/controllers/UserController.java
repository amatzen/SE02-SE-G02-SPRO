package dk.sdu.swe.domain.controllers;

import com.sendgrid.Content;
import com.sendgrid.Email;
import dk.sdu.swe.cross_cutting.exceptions.UserCreationException;
import dk.sdu.swe.cross_cutting.helpers.Utilities;
import dk.sdu.swe.cross_cutting.provider.EmailProvider;
import dk.sdu.swe.domain.controllers.contracts.IUserController;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.IUserDAO;
import dk.sdu.swe.persistence.dao.UserDAOImpl;

import java.util.Comparator;
import java.util.List;

public class UserController implements IUserController {

    private static IUserController userControllerInstance;
    private final IUserDAO userDAO;

    private UserController() {
        userDAO = UserDAOImpl.getInstance();
    }

    public static synchronized IUserController getInstance() {
        if (userControllerInstance == null) {
            userControllerInstance = new UserController();
        }
        return userControllerInstance;
    }

    @Override
    public User createUser(String username, String email, String name, Company company) throws UserCreationException {
        String pw = Utilities.createRandomPassword(12);

        User user = new User(username, email, name, pw, company);
        try {
            userDAO.save(user);
            company.getUsers().add(user);

            Content content = new Content();
            content.setType("text/plain");
            StringBuilder contents = new StringBuilder();
            contents.append("Kære " + name + ",\n\n");
            contents.append("Du er blevet oprettet på CrMS-platformen som producent hos " + company.getName() + ".\n");
            contents.append("\nDit brugernavn er " + username);
            contents.append("\nDin adgangskode er " + pw + "\n\n");
            contents.append("Ved spørgsmål bør du henvende dig til din administrator.\nDu er blevet oprettet af " + AuthController.getInstance().getUser().getName() + " (" + AuthController.getInstance().getUser().getCompany().getName() + ").");

            content.setValue(contents.toString());

            EmailProvider.sendEmail(new Email(email), "Du er blevet oprettet på CrMS", content);

            return user;
        } catch (Exception e) {
            throw new UserCreationException("Could not create user.", e);
        }
    }

    @Override
    public void delete(User user) {
        user.getCompany().getUsers().remove(user);
        userDAO.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userDAO.getAll();
        users.sort(Comparator.comparing(User::getUsername));
        return users;
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
