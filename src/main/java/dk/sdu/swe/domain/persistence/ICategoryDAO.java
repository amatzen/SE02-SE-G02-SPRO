package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Category;

import java.util.Optional;

public interface ICategoryDAO extends IDAO<Category> {

    public Optional<Category> getCategoryByName(String categoryName);

}
