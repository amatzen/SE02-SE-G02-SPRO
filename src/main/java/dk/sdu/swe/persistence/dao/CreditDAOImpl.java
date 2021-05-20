package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.persistence.ICreditDAO;

/**
 * The type Credit dao.
 */
public class CreditDAOImpl extends AbstractDAO<Credit> implements ICreditDAO {

    private static CreditDAOImpl instance;

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

    private CreditDAOImpl() {
        super(Credit.class);
    }
}
