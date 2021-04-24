package dk.sdu.swe.data;

import dk.sdu.swe.models.Person;
import dk.sdu.swe.models.Programme;
import dk.sdu.swe.models.User;

import java.util.List;

public class DBHandler implements PersistenceContract {
    @Override
    public List<User> getUsers() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getUser(int id) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createUser(User user) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(int id, User user) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(int id) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Person> getPeople() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Person getPerson() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createPerson(Person person) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updatePerson(int id, Person person) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deletePerson(int id) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Programme> getProgrammes() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Person getProgramme() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createProgramme(Programme programme) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateProgramme(int id, Programme programme) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteProgramme(int id) throws Exception {
        throw new UnsupportedOperationException();
    }
}
