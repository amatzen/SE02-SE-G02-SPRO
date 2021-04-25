package dk.sdu.swe.data;

import dk.sdu.swe.domain.models.*;

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
    public Person getPerson(int id) throws Exception {
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
    public Programme getProgramme(int id) throws Exception {
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

    @Override
    public List<Company> getCompanies() throws Exception {
        return null;
    }

    @Override
    public Company getCompany(int id) throws Exception {
        return null;
    }

    @Override
    public void createCompany(Company company) throws Exception {

    }

    @Override
    public void updateCompany(int id, Company company) throws Exception {

    }

    @Override
    public void deleteCompany(int id) throws Exception {

    }

    @Override
    public List<Credit> getCredits() throws Exception {
        return null;
    }

    @Override
    public Credit getCredit(int id) throws Exception {
        return null;
    }

    @Override
    public void createCredit(Credit credit) throws Exception {

    }

    @Override
    public void updateCredit(int id, Credit credit) throws Exception {

    }

    @Override
    public void deleteCredit(int id) throws Exception {

    }
}
