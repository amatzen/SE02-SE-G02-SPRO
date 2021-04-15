package dk.sdu.swe.data;

import dk.sdu.swe.models.User;

import java.util.List;

public interface PersistenceContract {

    public List<User> getUsers() throws Exception;

    public User getUser(int id) throws Exception;

    public void createUser(User user) throws Exception;

}
