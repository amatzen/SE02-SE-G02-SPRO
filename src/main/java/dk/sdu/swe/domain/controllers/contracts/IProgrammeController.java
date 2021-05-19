package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;
import java.util.Set;

public interface IProgrammeController {
    List<Programme> getAll();

    List<Programme> search(String searchTerm, Channel channel, Category category);

    List<Category> getCategories();

    Programme createProgramme(String title, int prodYear, Channel channel, Set<Category> categories, Company company);

    void updateProgramme(Programme programmeObj);
}
