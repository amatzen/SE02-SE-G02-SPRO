package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.PersonDAOImpl;
import dk.sdu.swe.domain.controllers.contracts.IPersonController;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.persistence.IPersonDAO;
import dk.sdu.swe.exceptions.PersonCreationException;
import dk.sdu.swe.helpers.PubSub;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

public class PersonController implements IPersonController {

    private IPersonDAO personDAO;
    private static IPersonController instance;

    private PersonController() {
        this.personDAO = PersonDAOImpl.getInstance();
    }

    public static IPersonController getInstance() {
        if (instance == null) {
            instance = new PersonController();
        }
        return instance;
    }

    @Override
    public List<Person> getAll() {
        List<Person> people = PersonDAOImpl.getInstance().getAll();
        people.sort(Comparator.comparing(Person::getName));
        return people;
    }

    @Override
    public List<Person> search(String searchTerm) {
        List<Person> people = PersonDAOImpl.getInstance().searchByName(searchTerm);
        people.sort(Comparator.comparing(Person::getName));
        return people;
    }

    @Override
    public void delete(Person person) {
        PersonDAOImpl.getInstance().delete(person);
    }

    @Override
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

    @Override
    public void update(Person personObj) {
        personDAO.update(personObj);
        PubSub.publish("trigger_update:person:refresh", true);
    }


    @Override
    public void merge(Person person1, Person person2) {
        personDAO.merge(person1, person2);
    }
}
