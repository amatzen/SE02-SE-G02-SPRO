package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Category;

import java.util.Optional;

/**
 * The interface Category dao.
 */
public interface ICategoryDAO extends IDAO<Category> {

    /**
     * Gets category by name.
     *
     * @param categoryName the category name
     * @return the category by name
     */
    public Optional<Category> getCategoryByName(String categoryName);

}
