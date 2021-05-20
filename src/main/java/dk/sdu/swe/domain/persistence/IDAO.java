package dk.sdu.swe.domain.persistence;

import java.util.List;
import java.util.Optional;

/**
 * The interface Idao.
 *
 * @param <T> the type parameter
 */
public interface IDAO<T> {

    /**
     * Save.
     *
     * @param obj the obj
     */
    public void save(T obj);

    /**
     * Update.
     *
     * @param obj the obj
     */
    public void update(T obj);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public Optional<T> getById(Long id);

    /**
     * Delete.
     *
     * @param obj the obj
     */
    public void delete(T obj);

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<T> getAll();

}
