package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.persistence.ICreditRoleDAO;

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
}
