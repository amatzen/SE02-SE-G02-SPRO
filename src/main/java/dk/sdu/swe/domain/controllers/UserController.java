package dk.sdu.swe.domain.controllers;

import com.sendgrid.Content;
import com.sendgrid.Email;
import dk.sdu.swe.data.dao.UserDAOImpl;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.IUserDAO;
import dk.sdu.swe.exceptions.UserCreationException;
import dk.sdu.swe.helpers.Utilities;
import dk.sdu.swe.provider.EmailProvider;

public class UserController {

    private IUserDAO userDAO;

    private static UserController UserControllerInstance;

    private UserController() {
        userDAO = UserDAOImpl.getInstance();
    }

    public static synchronized UserController getInstance() {
        if (UserControllerInstance == null) {
            UserControllerInstance = new UserController();
        }
        return UserControllerInstance;
    }

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
