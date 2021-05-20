package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.domain.persistence.IReviewDAO;

import java.util.List;
import java.util.Objects;

public class ReviewDAOImpl extends AbstractDAO<Review> implements IReviewDAO {
    private static ReviewDAOImpl instance;

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

    public Review getLatestReview(Programme programme) {
        return getAll().stream()
            .filter(x -> Objects.equals(x.getProgramme().getId(), programme.getId()))
            .findFirst().orElse(null);
    }
}
