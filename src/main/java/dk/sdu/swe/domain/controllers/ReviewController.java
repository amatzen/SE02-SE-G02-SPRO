package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.controllers.contracts.IReviewController;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.domain.persistence.IProgrammeDAO;
import dk.sdu.swe.domain.persistence.IReviewDAO;
import dk.sdu.swe.persistence.dao.ReviewDAOImpl;

import java.util.List;
import java.util.Optional;

/**
 * The type Review controller.
 */
public class ReviewController implements IReviewController {

    private static IReviewController reviewControllerInstance;

    private final IReviewDAO reviewDAO;

    private ReviewController() {
        reviewDAO = ReviewDAOImpl.getInstance();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized IReviewController getInstance() {
        if (reviewControllerInstance == null) {
            reviewControllerInstance = new ReviewController();
        }
        return reviewControllerInstance;
    }

    @Override
    public List<Review> getAll() {
        return reviewDAO.getAll();
    }

    @Override
    public void save(Review review) {
        reviewDAO.save(review);
    }

    @Override
    public void update(Review review) {
        reviewDAO.update(review);
    }

    public Optional<Review> getLatestReview(Programme programme) {
        return reviewDAO.getLatestReview(programme);
    }
}
