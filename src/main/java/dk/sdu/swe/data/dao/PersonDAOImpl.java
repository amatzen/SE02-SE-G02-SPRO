package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Person;

public class PersonDAOImpl extends AbstractDAO<Person> {

    private static PersonDAOImpl instance;

    public static PersonDAOImpl getInstance() {
        if (instance == null) {
            instance = new PersonDAOImpl();
        }
        return instance;
    }

    private PersonDAOImpl() {
        super(Person.class);
    }
}
