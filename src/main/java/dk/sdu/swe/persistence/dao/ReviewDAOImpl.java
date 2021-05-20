package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.domain.persistence.IReviewDAO;
import dk.sdu.swe.persistence.DB;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * The type Review dao.
 */
public class ReviewDAOImpl extends AbstractDAO<Review> implements IReviewDAO {
    private static ReviewDAOImpl instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public synchronized static ReviewDAOImpl getInstance() {
        if (instance == null) {
            instance = new ReviewDAOImpl();
        }
        return instance;
    }
    private ReviewDAOImpl() {
        super(Review.class);
    }

    @Override
    public List<Review> getAll() {
        return super.getAll();
    }

    /**
     * Gets latest review.
     *
     * @param programme the programme
     * @return the latest review
     */
    public Optional<Review> getLatestReview(Programme programme) {
        Session session = DB.openSession();
        Transaction t = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Review WHERE programme = :p ORDER BY id DESC");
            query.setMaxResults(1);
            query.setParameter("p", programme);

            return query.stream().reduce((o, o2) -> o2);
        } finally {
            t.commit();
            session.close();
        }
    }
}
