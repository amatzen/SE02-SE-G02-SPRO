package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;
import org.hibernate.Session;
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

        Session session = DB.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Programme> criteriaQuery = criteriaBuilder.createQuery(Programme.class);
        Root<Programme> programmeRoot = criteriaQuery.from(Programme.class);
        criteriaQuery.select(programmeRoot);

        List<Programme> res = session.createQuery(criteriaQuery).getResultList();

        session.close();

        return res;
    }

    public List<Programme> search(String searchTerm, Integer channelId, String category) {

        searchTerm = '%' + searchTerm + '%';

        String hql = "FROM Programme WHERE title LIKE :search_term";

        if (channelId != null) {
            hql += " AND channel = :channel_id";
        }

        if (category != null) {
            hql += " AND category = :category_title";
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

    public List<String> getCategories() {
        String hql = "SELECT distinct category FROM Programme";

        Session session = DB.openSession();

        Query query = session.createQuery(hql);
        List<String> res = query.list();

        session.close();

        return res;
    }

}
