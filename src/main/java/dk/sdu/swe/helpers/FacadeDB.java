package dk.sdu.swe.helpers;

import dk.sdu.swe.data.DBHandler;
import dk.sdu.swe.data.JSONHandler;
import dk.sdu.swe.data.PersistenceContract;
import dk.sdu.swe.models.User;

import java.util.List;

public class FacadeDB implements  PersistenceContract {

    private final JSONHandler jsonHandler = JSONHandler.getInstance();
    private final PersistenceContract dbHandler = new DBHandler();

    private PersistenceContract activeHandler;

    private User user = new User();

    public FacadeDB() {
        if (EnvironmentSelector.getInstance().getEnvironment() == Environment.FLATFILE) {
            this.activeHandler = jsonHandler;
        } else {
            this.activeHandler = dbHandler;
        }
    }

    public List<User> getUsers() throws Exception {
        return this.activeHandler.getUsers();
    }

    @Override
    public User getUser(int id) throws Exception {
      if (EnvironmentSelector.getInstance().getEnvironment()==Environment.FLATFILE) {
          jsonHandler.getUser(id);
      } else {
          dbHandler.getUser(id);
      }

        return null; //Skal ændres
    }

    @Override
    public void createUser(User user) throws Exception {

    }

    // get users metode for json
    //  get users metode for db handler
    // create user for json
    // create user for db handler
}
