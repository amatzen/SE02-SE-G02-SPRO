package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;

/**
 * The interface Credit controller.
 */
public interface ICreditController {
    /**
     * Create credit credit.
     *
     * @param person     the person
     * @param creditRole the credit role
     * @param programme  the programme
     * @return the credit
     */
    Credit createCredit(Person person, CreditRole creditRole, Programme programme);

    /**
     * Update.
     *
     * @param creditObj the credit obj
     */
    void update(Credit creditObj);

    /**
     * Delete.
     *
     * @param credit the credit
     */
    void delete(Credit credit);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Credit> getAll();

    void save(Credit credit);
}
