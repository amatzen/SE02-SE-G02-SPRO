package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;

public interface IProgrammeDAO extends IDAO<Programme> {

    public List<Programme> search(String searchTerm, Channel channel, Category category);

}
