package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CompanyDAOImpl;
import dk.sdu.swe.domain.models.Company;

import java.util.List;

public class CompanyController {

    private static CompanyController instance;

    private CompanyController() {}

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
}
