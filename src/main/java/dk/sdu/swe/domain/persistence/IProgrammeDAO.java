package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;

/**
 * The interface Programme dao.
 */
public interface IProgrammeDAO extends IDAO<Programme> {

    /**
     * Search list.
     *
     * @param searchTerm the search term
     * @param channel    the channel
     * @param category   the category
     * @return the list
     */
    public List<Programme> search(String searchTerm, Channel channel, Category category);

}
