package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class CategoryDAOImpl extends AbstractDAO<Category> implements ICategoryDAO {

    private static CategoryDAOImpl instance;

    public static CategoryDAOImpl getInstance() {
        if (instance == null) {
            instance = new CategoryDAOImpl();
        }
        return instance;
    }

    private CategoryDAOImpl() {
        super(Category.class);
    }

    @Override
    public Optional<Category> getCategoryByName(String categoryName) {
        Session session = DB.openSession();
        Transaction transaction = session.beginTransaction();
        Category category = null;
        try {
            category = session.byNaturalId(Category.class)
                .using("categoryTitle", categoryName).load();
            transaction.commit();
        } finally {
            session.close();
        }
        return Optional.ofNullable(category);
    }
}
