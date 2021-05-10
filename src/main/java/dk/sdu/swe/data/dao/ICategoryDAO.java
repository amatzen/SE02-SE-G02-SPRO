package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Category;

import java.util.Optional;

public interface ICategoryDAO extends IDAO<Category> {

    public Optional<Category> getCategoryByName(String categoryName);

}
