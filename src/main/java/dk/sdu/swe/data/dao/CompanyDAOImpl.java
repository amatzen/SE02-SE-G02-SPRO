package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Company;

public class CompanyDAOImpl extends AbstractDAO<Company> {

    private static CompanyDAOImpl instance;

    public static CompanyDAOImpl getInstance() {
        if (instance == null) {
            instance = new CompanyDAOImpl();
        }
        return instance;
    }

    private CompanyDAOImpl() {
        super(Company.class);
    }

}
