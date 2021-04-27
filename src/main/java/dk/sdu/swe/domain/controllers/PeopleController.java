package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.FacadeDB;
import dk.sdu.swe.domain.models.Person;

import java.util.List;

public class PeopleController {

    private static PeopleController instance;

    private PeopleController() {}

    public static PeopleController getInstance() {
        if (instance == null) {
            instance = new PeopleController();
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
