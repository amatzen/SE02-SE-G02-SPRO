package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.FacadeDB;
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

    public List<Person> getAll() throws Exception {
        return FacadeDB.getInstance().getPeople();
    }

    public Person get(int id) throws Exception {
        return FacadeDB.getInstance().getPerson(id);
    }

}
