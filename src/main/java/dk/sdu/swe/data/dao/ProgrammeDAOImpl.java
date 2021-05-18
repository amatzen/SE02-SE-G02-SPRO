package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.persistence.IProgrammeDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;
import java.util.stream.Collectors;

public class ProgrammeDAOImpl extends AbstractDAO<Programme> implements IProgrammeDAO {
    private static ProgrammeDAOImpl instance;

    public synchronized static IProgrammeDAO getInstance() {
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

        String hql = "FROM Programme p JOIN FETCH p.categories WHERE p.title LIKE :search_term";

        if (channel != null) {
            hql += " AND channel_id = :channel_id";
        }

        if (category != null) {
            hql += " AND :category_title IN (SELECT categoryTitle FROM p.categories)";
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

        List<Programme> res = (List<Programme>) query.list().stream().distinct().collect(Collectors.toList());

        session.close();

        return res;
    }

    @Override
    public List<Programme> getAll() {
        Session session = DB.openSession();
        Transaction transaction = session.beginTransaction();

        List<Programme> result;
        try {
            result = (List<Programme>) session.createQuery(
                "FROM Programme as p " +
                "INNER JOIN FETCH p.categories " +
                "INNER JOIN FETCH p.channel").list().stream().distinct().collect(Collectors.toList());
        } finally {
            transaction.commit();
            session.close();
        }
        return result;
    }
}
