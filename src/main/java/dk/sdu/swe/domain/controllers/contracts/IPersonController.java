package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.exceptions.PersonCreationException;

import java.time.ZonedDateTime;
import java.util.List;

public interface IPersonController {
    List<Person> getAll();

    List<Person> search(String searchTerm);

    void delete(Person person);

    Person createPerson(String name, String image, String email, ZonedDateTime bday) throws PersonCreationException;

    void update(Person personObj);

    void merge(Person person1, Person person2);
}