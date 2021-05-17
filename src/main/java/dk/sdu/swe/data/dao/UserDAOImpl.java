package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.IUserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class UserDAOImpl extends AbstractDAO<User> implements IUserDAO {
    private static UserDAOImpl instance;

    public synchronized static UserDAOImpl getInstance() {
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
        User user = null;
        Transaction trans = session.beginTransaction();
        try {
            user = session.byNaturalId(User.class)
                .using("username", username).load();
        } finally {
            trans.commit();
            session.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void promoteUser(User user, String type) {
        Session session = DB.openSession();
        String hql = "UPDATE User SET usertype = :usertype WHERE id = :id";
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery(hql);
            query.setParameter("usertype", type);
            query.setParameter("id", user.getId());
            query.executeUpdate();
        } finally {
            transaction.commit();
            session.close();
        }

    }
}
