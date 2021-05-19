package dk.sdu.swe.domain.persistence;

import java.util.List;
import java.util.Optional;

public interface IDAO<T> {

    public void save(T obj);

    public void update(T obj);

    public Optional<T> getById(Long id);

    public void delete(T obj);

    public List<T> getAll();

}
