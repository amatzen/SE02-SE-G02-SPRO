package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.IUserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserDAOImpl extends AbstractDAO<User> implements IUserDAO {
    private static UserDAOImpl instance;

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    private UserDAOImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        Session session = DB.openSession();
        Transaction trans = session.beginTransaction();
        User user = session.byNaturalId(User.class)
            .using("username", username).load();
        trans.commit();
        session.close();
        return Optional.ofNullable(user);
    }
}
