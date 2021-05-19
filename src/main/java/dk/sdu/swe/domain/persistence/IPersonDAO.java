package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Person;

import java.util.List;

public interface IPersonDAO extends IDAO<Person> {

    public List<Person> searchByName(String name);

    public void merge(Person person1, Person person2);
}
