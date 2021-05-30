package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.persistence.ICreditRoleDAO;
import dk.sdu.swe.persistence.DB;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * The type Credit role dao.
 */
public class CreditRoleDAOImpl extends AbstractDAO<CreditRole> implements ICreditRoleDAO {
    private static CreditRoleDAOImpl instance;

    /**
     * Instantiates a new Credit role dao.
     */
    public CreditRoleDAOImpl() {
        super(CreditRole.class);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public synchronized static ICreditRoleDAO getInstance() {
        if (instance == null) {
            instance = new CreditRoleDAOImpl();
        }
        return instance;
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
