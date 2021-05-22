package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.cross_cutting.exceptions.PersonCreationException;
import dk.sdu.swe.domain.models.Person;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * The interface Person controller.
 */
public interface IPersonController {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<Person> getAll();

    /**
     * Search list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    List<Person> search(String searchTerm);

    /**
     * Delete.
     *
     * @param person the person
     */
    void delete(Person person);

    /**
     * Create person person.
     *
     * @param name  the name
     * @param image the image
     * @param email the email
     * @param bday  the bday
     * @return the person
     * @throws PersonCreationException the person creation exception
     */
    Person createPerson(String name, String image, String email, ZonedDateTime bday) throws PersonCreationException;

    /**
     * Update.
     *
     * @param personObj the person obj
     */
    void update(Person personObj);

    /**
     * Merge.
     *
     * @param person1 the person 1
     * @param person2 the person 2
     */
    void merge(Person person1, Person person2);
}
