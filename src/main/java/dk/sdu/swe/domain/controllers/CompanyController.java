package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CompanyDAOImpl;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.CompanyDetails;
import dk.sdu.swe.domain.persistence.ICompanyDAO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if(AuthController.getInstance().getUser().hasPermission("companies.list.all")) {
            return CompanyDAOImpl.getInstance().getAll();
        }

        return CompanyDAOImpl.getInstance().getAll()
            .stream()
            .filter(x -> {
                boolean userCompany = Objects.equals(x.getId(), AuthController.getInstance().getUser().getCompany().getId());
                boolean subCompany = false;

                Company currentCompany = x.getParentCompany();
                while(Objects.nonNull(currentCompany)) {
                    subCompany = Objects.equals(currentCompany.getId(), AuthController.getInstance().getUser().getCompany().getId());
                    currentCompany = currentCompany.getParentCompany();
                }


                return userCompany || subCompany;
            })
            .collect(Collectors.toList());

    }

    public List<Company> search(String searchTerm) {
        return CompanyDAOImpl.getInstance().search(searchTerm);
    }

    public Company createCompany(String company, String cvr, String address) {
        CompanyDetails companyDetails = new CompanyDetails(address, null, cvr);
        Company companyObj = new Company(company, null, companyDetails, null);
        companyDAO.save(companyObj);
        return companyObj;
    }
}
