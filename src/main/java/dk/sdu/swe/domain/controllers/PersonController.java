package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.PersonDAOImpl;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.persistence.IPersonDAO;
import dk.sdu.swe.exceptions.PersonCreationException;
import dk.sdu.swe.helpers.PubSub;

import java.time.ZonedDateTime;
import java.util.Comparator;
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
        List<Person> people = PersonDAOImpl.getInstance().getAll();
        people.sort(Comparator.comparing(Person::getName));
        return people;
    }

    public List<Person> search(String searchTerm) {
        List<Person> people = PersonDAOImpl.getInstance().searchByName(searchTerm);
        people.sort(Comparator.comparing(Person::getName));
        return people;
    }

    public void delete(Person person) {
        PersonDAOImpl.getInstance().delete(person);
    }

    public Person createPerson(String name, String image, String email, ZonedDateTime bday) throws PersonCreationException {
        if (image == null) {
            image = "https://via.placeholder.com/150";
        }
        Person person = null;
        try {
            person = new Person(name, image, email, bday);
            personDAO.save(person);
            PubSub.publish("trigger_update:person:refresh", true);
        } catch (PersonCreationException e) {
            throw e;
        }
        return person;
    }

    public void update(Person personObj) {
        personDAO.update(personObj);
        PubSub.publish("trigger_update:person:refresh", true);
    }


    public void merge(Person person1, Person person2) {
        personDAO.merge(person1, person2);
    }
}
