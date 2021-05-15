package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.persistence.ICreditDAO;

public class CreditDAOImpl extends AbstractDAO<Credit> implements ICreditDAO {

    private static CreditDAOImpl instance;

    public static ICreditDAO getInstance() {
        if (instance == null) {
            instance = new CreditDAOImpl();
        }
        return instance;
    }

    private CreditDAOImpl() {
        super(Credit.class);
    }
}
