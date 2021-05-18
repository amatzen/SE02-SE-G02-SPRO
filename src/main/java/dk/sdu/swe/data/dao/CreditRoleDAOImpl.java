package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.persistence.ICreditRoleDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CreditRoleDAOImpl extends AbstractDAO<CreditRole> implements ICreditRoleDAO {
    private static CreditRoleDAOImpl instance;

    public synchronized static ICreditRoleDAO getInstance() {
        if (instance == null) {
            instance = new CreditRoleDAOImpl();
        }
        return instance;
    }

    public CreditRoleDAOImpl() {
        super(CreditRole.class);
    }

    @Override
    public void delete(CreditRole obj) {
        Session session = DB.openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("DELETE Credit WHERE role_id = :role_id");
            query.setParameter("role_id", obj.getId());
            query.executeUpdate();
            session.delete(obj);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
