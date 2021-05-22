package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Person;

import java.util.List;

/**
 * The interface Person dao.
 */
public interface IPersonDAO extends IDAO<Person> {

    /**
     * Search by name list.
     *
     * @param name the name
     * @return the list
     */
    public List<Person> searchByName(String name);

    /**
     * Merge.
     *
     * @param person1 the person 1
     * @param person2 the person 2
     */
    public void merge(Person person1, Person person2);
}
