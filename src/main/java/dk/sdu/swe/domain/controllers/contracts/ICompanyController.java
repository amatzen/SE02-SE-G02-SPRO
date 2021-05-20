package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Company;

import java.util.List;

/**
 * The interface Company controller.
 */
public interface ICompanyController {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<Company> getAll();

    /**
     * Search list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    List<Company> search(String searchTerm);

    /**
     * Create company company.
     *
     * @param company the company
     * @param cvr     the cvr
     * @param address the address
     * @return the company
     */
    Company createCompany(String company, String cvr, String address);

    /**
     * Update.
     *
     * @param company the company
     */
    void update(Company company);

    /**
     * Get company.
     *
     * @param id the id
     * @return the company
     */
    Company get(Long id);
}
