package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.persistence.ICreditDAO;
import dk.sdu.swe.persistence.DB;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The type Credit dao.
 */
public class CreditDAOImpl extends AbstractDAO<Credit> implements ICreditDAO {

    private static CreditDAOImpl instance;

    private CreditDAOImpl() {
        super(Credit.class);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public synchronized static ICreditDAO getInstance() {
        if (instance == null) {
            instance = new CreditDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Credit> getCredits(Programme programme) {
        String hql = "FROM Credit WHERE programme = :programme";

        Session session = DB.openSession();
        Transaction transaction = session.beginTransaction();
        List<Credit> credits = null;
        try {
            Query query = session.createQuery(hql);
            query.setParameter("programme", programme);
            credits = query.list();
        } finally {
            transaction.commit();
            session.close();
        }
        return credits;
    }
}
