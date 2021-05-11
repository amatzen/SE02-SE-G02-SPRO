package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.persistence.ICompanyDAO;

public class CompanyDAOImpl extends AbstractDAO<Company> implements ICompanyDAO {

    private static CompanyDAOImpl instance;

    public static ICompanyDAO getInstance() {
        if (instance == null) {
            instance = new CompanyDAOImpl();
        }
        return instance;
    }

    private CompanyDAOImpl() {
        super(Company.class);
    }

}
