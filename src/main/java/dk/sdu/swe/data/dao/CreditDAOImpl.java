package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Credit;

public class CreditDAOImpl extends AbstractDAO<Credit> {

    private static CreditDAOImpl instance;

    public static CreditDAOImpl getInstance() {
        if (instance == null) {
            instance = new CreditDAOImpl();
        }
        return instance;
    }

    private CreditDAOImpl() {
        super(Credit.class);
    }
}
