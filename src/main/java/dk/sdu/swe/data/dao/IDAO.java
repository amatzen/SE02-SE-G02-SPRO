package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public interface IDAO<T> {

    public default void save(T obj) {
        Session session = DB.openSession();
        Transaction trans = session.beginTransaction();
        try {
            session.save(obj);
            trans.commit();
        } finally {
            session.close();
        }
    }

    public default void update(T obj) {
        Session session = DB.openSession();
        Transaction trans = session.beginTransaction();
        try {
            session.update(obj);
            trans.commit();
        } finally {
            session.close();
        }
    }

    public Optional<T> getById(int id);

    public List<T> getAll();

}
