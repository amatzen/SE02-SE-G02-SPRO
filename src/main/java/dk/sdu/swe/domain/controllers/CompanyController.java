package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CompanyDAOImpl;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.CompanyDetails;
import dk.sdu.swe.domain.persistence.ICompanyDAO;

import java.util.List;

public class CompanyController {

    private ICompanyDAO companyDAO;
    private static CompanyController instance;

    private CompanyController() {
        companyDAO = CompanyDAOImpl.getInstance();
    }

    public static CompanyController getInstance() {
        if (instance == null) {
            instance = new CompanyController();
        }
        return instance;
    }

    public List<Company> getAll() {
        return CompanyDAOImpl.getInstance().getAll();
    }

    public List<Company> search(String searchTerm) {
        return CompanyDAOImpl.getInstance().search(searchTerm);
    }

    public Company createCompany(String company, String cvr, String address) {
        CompanyDetails companyDetails = new CompanyDetails(null, null, cvr);
        Company companyObj = new Company(company, null, companyDetails, null);
        companyDAO.save(companyObj);
        return companyObj;
    }
}
