package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.persistence.IProgrammeDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProgrammeDAOImpl extends AbstractDAO<Programme> implements IProgrammeDAO {
    private static ProgrammeDAOImpl instance;

    public static IProgrammeDAO getInstance() {
        if (instance == null) {
            instance = new ProgrammeDAOImpl();
        }
        return instance;
    }

    private ProgrammeDAOImpl() {
        super(Programme.class);
    }

    public List<Programme> search(String searchTerm, Channel channel, Category category) {
        searchTerm = '%' + searchTerm + '%';

        String hql = "FROM Programme p WHERE p.title LIKE :search_term";

        if (channel != null) {
            hql += " AND channel_id = :channel_id";
        }

        if (category != null) {
            hql += " AND :category_title IN (SELECT c.categoryTitle FROM Category c JOIN p.categories WHERE channel_id = :channel_id)";
        }

        Session session = DB.openSession();

        Query query = session.createQuery(hql);
        query.setParameter("search_term", searchTerm);

        if (channel != null) {
            query.setParameter("channel_id", channel.getId());
        }

        if (category != null) {
            query.setParameter("category_title", category.getCategoryTitle());
        }

        List<Programme> res = query.list();

        session.close();

        return res;
    }

    @Override
    public List<Programme> getAll() {
        Session session = DB.openSession();
        Transaction transaction = session.beginTransaction();

        List<Programme> result;
        try {
            result = session.createQuery("FROM Programme as programme JOIN FETCH programme.categories").list();
        } finally {
            transaction.commit();
            session.close();
        }
        return result;
    }
}
