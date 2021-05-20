package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.persistence.DB;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.persistence.IPersonDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

/**
 * The type Person dao.
 */
public class PersonDAOImpl extends AbstractDAO<Person> implements IPersonDAO {

    private static PersonDAOImpl instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public synchronized static PersonDAOImpl getInstance() {
        if (instance == null) {
            instance = new PersonDAOImpl();
        }
        return instance;
    }

    private PersonDAOImpl() {
        super(Person.class);
    }

    @Override
    public List<Person> searchByName(String searchTerm) {
        searchTerm = '%' + searchTerm + '%';
        String hql = "FROM Person WHERE name LIKE :search_term";

        Session session = DB.openSession();
        Transaction trans = session.beginTransaction();

        List<Person> personList = null;
        try {
            Query query = session.createQuery(hql);
            query.setParameter("search_term", searchTerm);
            personList = query.list();
        } finally {
            trans.commit();
            session.close();
        }

        return personList;
    }

    @Override
    public void merge(Person person1, Person person2) {
        String sql = "UPDATE Credit SET person_id = :person1_id WHERE person_id = :person2_id";

        Session session = DB.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(sql);
            query.setParameter("person1_id", person1.getId());
            query.setParameter("person2_id", person2.getId());
            query.executeUpdate();
            session.delete(person2);
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Person obj) {
        Session session = DB.openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("DELETE Credit WHERE person_id = :person_id");
            query.setParameter("person_id", obj.getId());
            query.executeUpdate();
            session.delete(obj);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
