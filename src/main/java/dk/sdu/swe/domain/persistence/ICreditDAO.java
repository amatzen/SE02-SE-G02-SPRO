package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;

/**
 * The interface Credit dao.
 */
public interface ICreditDAO extends IDAO<Credit> {
    List<Credit> getCredits(Programme programme);
}
