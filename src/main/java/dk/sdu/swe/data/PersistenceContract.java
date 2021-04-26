package dk.sdu.swe.data;

import dk.sdu.swe.domain.models.*;

import java.util.List;

public interface PersistenceContract {

    public List<User> getUsers() throws Exception;
    public User getUser(int id) throws Exception;
    public void createUser(User user) throws Exception;
    public void updateUser(int id, User user) throws Exception;
    public void deleteUser(int id) throws Exception;

    public List<Person> getPeople() throws Exception;
    public Person getPerson(int id) throws Exception;
    public void createPerson(Person person) throws Exception;
    public void updatePerson(int id, Person person) throws Exception;
    public void deletePerson(int id) throws Exception;

    public List<Programme> getProgrammes() throws Exception;
    public Programme getProgramme(int id) throws Exception;
    public void createProgramme(Programme programme) throws Exception;
    public void updateProgramme(int id, Programme programme) throws Exception;
    public void deleteProgramme(int id) throws Exception;

    public List<Company> getCompanies() throws Exception;
    public Company getCompany(int id) throws Exception;
    public void createCompany(Company company) throws Exception;
    public void updateCompany(int id, Company company) throws Exception;
    public void deleteCompany(int id) throws Exception;

    public List<Credit> getCredits() throws Exception;
    public Credit getCredit(int id) throws Exception;
    public void createCredit(Credit credit) throws Exception;
    public void updateCredit(int id, Credit credit) throws Exception;
    public void deleteCredit(int id) throws Exception;

}
