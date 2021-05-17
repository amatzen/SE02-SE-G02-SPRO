package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Company;

import java.util.List;

public interface ICompanyDAO extends IDAO<Company> {
    public List<Company> search(String searchTerm);
}
