package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.domain.persistence.IReviewDAO;
import dk.sdu.swe.persistence.DB;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

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
    public Review getLatestReview(Programme programme) {
        Session session = DB.openSession();
        Transaction t = session.beginTransaction();

        Query query = session.createQuery("FROM Review WHERE Programme = :p ORDER BY id DESC");
        query.setParameter("p", programme);

        t.commit();
        session.close();

        return (Review) query.getResultList().get(query.getResultList().size() - 1);
    }
}
