package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.PersonDAOImpl;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.persistence.IPersonDAO;

import java.time.ZonedDateTime;
import java.util.List;

public class PersonController {

    private IPersonDAO personDAO;
    private static PersonController instance;

    private PersonController() {
        this.personDAO = PersonDAOImpl.getInstance();
    }

    public static PersonController getInstance() {
        if (instance == null) {
            instance = new PersonController();
        }
        return instance;
    }

    public List<Person> getAll() {
        return PersonDAOImpl.getInstance().getAll();
    }

    public List<Person> search(String searchTerm) {
        return PersonDAOImpl.getInstance().searchByName(searchTerm);
    }

    public void delete(Person person) {
        PersonDAOImpl.getInstance().delete(person);
    }

    public Person createPerson(String name, String image, String email, ZonedDateTime bday) {
        Person person = new Person(name, image, bday);
        person.putContactDetail("email", email);
        personDAO.save(person);
        return person;
    }

    public Person createPerson(String name, String image, ZonedDateTime bday) {
        Person person = new Person(name, image, bday);
        personDAO.save(person);
        return person;
    }

    public void update(Person personObj) {
        personDAO.update(personObj);
    }
}
