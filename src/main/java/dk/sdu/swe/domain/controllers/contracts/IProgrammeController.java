package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;
import java.util.Set;

/**
 * The interface Programme controller.
 */
public interface IProgrammeController {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<Programme> getAll();

    /**
     * Search list.
     *
     * @param searchTerm the search term
     * @param channel    the channel
     * @param category   the category
     * @return the list
     */
    List<Programme> search(String searchTerm, Channel channel, Category category);

    /**
     * Gets categories.
     *
     * @return the categories
     */
    List<Category> getCategories();

    /**
     * Create programme programme.
     *
     * @param title      the title
     * @param prodYear   the prod year
     * @param channel    the channel
     * @param categories the categories
     * @param company    the company
     * @return the programme
     */
    Programme createProgramme(String title, int prodYear, Channel channel, Set<Category> categories, Company company);

    /**
     * Update programme.
     *
     * @param programmeObj the programme obj
     */
    void update(Programme programmeObj);
}
