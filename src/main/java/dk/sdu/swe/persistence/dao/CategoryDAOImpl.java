package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.persistence.ICategoryDAO;
import dk.sdu.swe.persistence.DB;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

/**
 * The type Category dao.
 */
public class CategoryDAOImpl extends AbstractDAO<Category> implements ICategoryDAO {

    private static CategoryDAOImpl instance;

    private CategoryDAOImpl() {
        super(Category.class);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public synchronized static CategoryDAOImpl getInstance() {
        if (instance == null) {
            instance = new CategoryDAOImpl();
        }
        return instance;
    }

    @Override
    public Optional<Category> getCategoryByName(String categoryName) {
        Session session = DB.openSession();
        Transaction transaction = session.beginTransaction();
        Category category = null;
        try {
            category = session.byNaturalId(Category.class)
                .using("categoryTitle", categoryName).load();
        } finally {
            transaction.commit();
            session.close();
        }
        return Optional.ofNullable(category);
    }
}
