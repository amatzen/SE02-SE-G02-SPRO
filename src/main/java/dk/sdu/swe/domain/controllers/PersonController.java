package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.PersonDAOImpl;
import dk.sdu.swe.domain.models.Person;

import java.util.List;

public class PersonController {

    private static PersonController instance;

    private PersonController() {}

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
}
