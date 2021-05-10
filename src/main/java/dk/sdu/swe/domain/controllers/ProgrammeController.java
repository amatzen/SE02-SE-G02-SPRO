package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.data.dao.CategoryDAOImpl;
import dk.sdu.swe.data.dao.ProgrammeDAO;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProgrammeController {

    private static ProgrammeController instance;

    private ProgrammeController() {}

    public static ProgrammeController getInstance() {
        if (instance == null) {
            instance = new ProgrammeController();
        }
        return instance;
    }

    public List<Programme> getAll() {
        return ProgrammeDAO.getInstance().getAll();
    }

    public List<Programme> search(String searchTerm, Integer channelId, String category) {

        searchTerm = '%' + searchTerm + '%';

        String hql = "FROM Programme p WHERE p.title LIKE :search_term";

        if (channelId != null) {
            hql += " AND p.channel_id = :channel_id";
        }

        if (category != null) {
            hql += " AND :category_title IN (SELECT c.categoryTitle FROM Category c JOIN p.categories)";
        }

        Session session = DB.openSession();

        Query query = session.createQuery(hql);
        query.setParameter("search_term", searchTerm);

        if (channelId != null) {
            query.setParameter("channel_id", channelId);
        }

        if (category != null) {
            query.setParameter("category_title", category);
        }

        List<Programme> res = query.list();

        session.close();

        return res;
    }

    public List<Category> getCategories() {
        return CategoryDAOImpl.getInstance().getAll();
        /*
        String hql = "SELECT distinct categories FROM Programme";

        Session session = DB.openSession();

        Transaction trans = session.beginTransaction();
        Query query = session.createQuery(hql);
        List<Category> res = query.list();
        trans.commit();

        session.close();

        return res;
        */
    }

}
