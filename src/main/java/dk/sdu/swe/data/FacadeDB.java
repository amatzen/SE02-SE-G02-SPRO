package dk.sdu.swe.data;

import dk.sdu.swe.domain.models.*;
import dk.sdu.swe.helpers.Environment;
import dk.sdu.swe.helpers.EnvironmentSelector;

import java.util.List;

public class FacadeDB implements PersistenceContract {

    private PersistenceContract activeHandler;

    private User user = new User();

    private static FacadeDB instance;

    private FacadeDB() {
        if (EnvironmentSelector.getInstance().getEnvironment() == Environment.FLATFILE) {
            this.activeHandler = JSONHandler.getInstance();
        } else {
            this.activeHandler = new DBHandler();
        }
    }

    public static FacadeDB getInstance() {
        if (instance == null) {
            instance = new FacadeDB();
        }
        return instance;
    }

    public List<User> getUsers() throws Exception {
        return this.activeHandler.getUsers();
    }

    @Override
    public User getUser(int id) throws Exception {
        return activeHandler.getUser(id);
    }

    @Override
    public void createUser(User user) throws Exception {
        activeHandler.createUser(user);
    }

    @Override
    public void updateUser(int id, User user) throws Exception {
        activeHandler.updateUser(id, user);
    }

    @Override
    public void deleteUser(int id) throws Exception {
        activeHandler.deleteUser(id);
    }

    @Override
    public List<Person> getPeople() throws Exception {
        return activeHandler.getPeople();
    }

    @Override
    public Person getPerson(int id) throws Exception {
        return activeHandler.getPerson(id);
    }

    @Override
    public void createPerson(Person person) throws Exception {
        activeHandler.createPerson(person);
    }

    @Override
    public void updatePerson(int id, Person person) throws Exception {
        activeHandler.updatePerson(id, person);
    }

    @Override
    public void deletePerson(int id) throws Exception {
        activeHandler.deletePerson(id);
    }

    @Override
    public List<Programme> getProgrammes() throws Exception {
        return activeHandler.getProgrammes();
    }

    @Override
    public Programme getProgramme(int id) throws Exception {
        return activeHandler.getProgramme(id);
    }

    @Override
    public void createProgramme(Programme programme) throws Exception {
        activeHandler.createProgramme(programme);
    }

    @Override
    public void updateProgramme(int id, Programme programme) throws Exception {
        activeHandler.updateProgramme(id, programme);
    }

    @Override
    public void deleteProgramme(int id) throws Exception {
        activeHandler.deleteProgramme(id);
    }

    @Override
    public List<Company> getCompanies() throws Exception {
        return activeHandler.getCompanies();
    }

    @Override
    public Company getCompany(int id) throws Exception {
        return activeHandler.getCompany(id);
    }

    @Override
    public void createCompany(Company company) throws Exception {
        activeHandler.createCompany(company);
    }

    @Override
    public void updateCompany(int id, Company company) throws Exception {
        activeHandler.updateCompany(id, company);
    }

    @Override
    public void deleteCompany(int id) throws Exception {
        activeHandler.deleteCompany(id);
    }

    @Override
    public List<Credit> getCredits() throws Exception {
        return activeHandler.getCredits();
    }

    @Override
    public Credit getCredit(int id) throws Exception {
        return activeHandler.getCredit(id);
    }

    @Override
    public void createCredit(Credit credit) throws Exception {
        activeHandler.createCredit(credit);
    }

    @Override
    public void updateCredit(int id, Credit credit) throws Exception {
        activeHandler.updateCredit(id, credit);
    }

    @Override
    public void deleteCredit(int id) throws Exception {
        activeHandler.deleteCredit(id);
    }

}
