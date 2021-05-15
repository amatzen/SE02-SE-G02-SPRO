package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.data.DB;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IDAO<T> {

    public void save(T obj);

    public void update(T obj);

    public Optional<T> getById(Long id);

    public void delete(T obj);

    public List<T> getAll();

}
