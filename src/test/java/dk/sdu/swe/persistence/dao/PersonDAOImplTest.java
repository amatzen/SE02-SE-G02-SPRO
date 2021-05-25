package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.cross_cutting.exceptions.PersonCreationException;
import dk.sdu.swe.cross_cutting.helpers.Environment;
import dk.sdu.swe.cross_cutting.helpers.EnvironmentSelector;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.persistence.IPersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOImplTest {

    private IPersonDAO personDAO = PersonDAOImpl.getInstance();

    @BeforeEach
    void setUp() {
        EnvironmentSelector.getInstance().setEnvironment(Environment.LOCAL);
        System.out.println("Username: " + System.getenv("DATABASE_LOCAL_USER"));
    }

    @Test
    void getById() {

        ZonedDateTime bday = ZonedDateTime.now();
        Person person = null;

        try {
            person = new Person("Esben Sørensen", "pfp", "esben@esben.io", bday);
            personDAO.save(person);
        } catch (PersonCreationException e) {
            fail(e.getMessage());
        }

        Person finalPerson = person;
        personDAO.getById(person.getId()).ifPresent(dbPerson -> {
            assertEquals(dbPerson.getId(), finalPerson.getId());
            assertEquals(dbPerson.getName(), finalPerson.getName());
            assertEquals(dbPerson.getEmail(), finalPerson.getEmail());
            assertEquals(dbPerson.getImage(), finalPerson.getImage());
            assertEquals(dbPerson.getDateOfBirth(), finalPerson.getDateOfBirth());
        });

    }

    @Test
    void searchByName() {

        ZonedDateTime bday = ZonedDateTime.now();
        Person person = null;

        try {
            person = new Person("Esben Sørensen", "pfp", "esben@example.com", bday);
            personDAO.save(person);
            personDAO.save(new Person("Søren Matzen", "pfp", "søren@example.com", bday));
        } catch (PersonCreationException e) {
            fail(e.getMessage());
        }

        List<Person> personList = personDAO.searchByName("Esben");

        assertTrue(personList.size() == 1);

        assertTrue(personDAO.searchByName("Frank").size() == 0);

        assertTrue(personDAO.searchByName("Søren").size() == 2);

        assertTrue(isInList(person, personList));

        Person dbPerson = personDAO.getById(person.getId()).orElse(null);

        assertEquals(dbPerson.getId(), person.getId());
        assertEquals(dbPerson.getName(), person.getName());
        assertEquals(dbPerson.getEmail(), person.getEmail());
        assertEquals(dbPerson.getImage(), person.getImage());
        assertEquals(dbPerson.getDateOfBirth(), person.getDateOfBirth());
    }

    private boolean isInList(Person person, List<Person> personList) {
        return personList.stream()
            .map(person1 -> person1.getId())
            .anyMatch(id -> id.equals(person.getId()));
    }

}
