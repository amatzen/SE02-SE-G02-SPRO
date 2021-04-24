package dk.sdu.swe.data;

import dk.sdu.swe.models.Person;
import dk.sdu.swe.models.Programme;
import dk.sdu.swe.models.User;

import java.util.List;

public interface PersistenceContract {

    public List<User> getUsers() throws Exception;
    public User getUser(int id) throws Exception;
    public void createUser(User user) throws Exception;
    public void updateUser(int id, User user) throws Exception;
    public void deleteUser(int id) throws Exception;

    public List<Person> getPeople() throws Exception;
    public Person getPerson() throws Exception;
    public void createPerson(Person person) throws Exception;
    public void updatePerson(int id, Person person) throws Exception;
    public void deletePerson(int id) throws Exception;

    public List<Programme> getProgrammes() throws Exception;
    public Person getProgramme() throws Exception;
    public void createProgramme(Programme programme) throws Exception;
    public void updateProgramme(int id, Programme programme) throws Exception;
    public void deleteProgramme(int id) throws Exception;

}
