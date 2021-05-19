package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Company;

import java.util.List;

public interface ICompanyController {
    List<Company> getAll();

    List<Company> search(String searchTerm);

    Company createCompany(String company, String cvr, String address);

    void update(Company company);

    Company get(Long id);
}
