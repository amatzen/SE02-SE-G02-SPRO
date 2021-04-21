package dk.sdu.swe.data;

import dk.sdu.swe.models.User;

import java.util.List;

public class DBHandler implements PersistenceContract {
    @Override
    public List<User> getUsers() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getUser(int id) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createUser(User user) throws Exception {
        throw new UnsupportedOperationException();
    }
}
