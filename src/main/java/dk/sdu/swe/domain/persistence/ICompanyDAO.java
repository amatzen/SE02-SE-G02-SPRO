package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Company;

import java.util.List;

/**
 * The interface Company dao.
 */
public interface ICompanyDAO extends IDAO<Company> {
    /**
     * Search list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    public List<Company> search(String searchTerm);
}
