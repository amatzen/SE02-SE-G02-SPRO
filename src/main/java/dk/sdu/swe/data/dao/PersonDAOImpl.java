package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PersonDAOImpl extends AbstractDAO<Person> implements IPersonDAO {

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

    @Override
    public List<Person> searchByName(String name) {
        String hql = "FROM Person WHERE name like : %:name%";

        Session session = DB.openSession();
        Transaction trans = session.beginTransaction();

        Query query = session.createQuery(hql);
        List<Person> personList = query.list();

        trans.commit();
        session.close();
        return personList;
    }
}
