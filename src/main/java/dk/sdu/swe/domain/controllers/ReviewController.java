package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.ReviewDAOImpl;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.domain.persistence.IReviewDAO;

import java.util.List;

public class ReviewController {

    private static ReviewController ReviewControllerInstance;

    private IReviewDAO reviewDAO;

    private ReviewController() {
        reviewDAO = ReviewDAOImpl.getInstance();
    }

    public static synchronized ReviewController getInstance() {
        if (ReviewControllerInstance == null) {
            ReviewControllerInstance = new ReviewController();
        }
        return ReviewControllerInstance;
    }

    public List<Review> getAll() {
        return reviewDAO.getAll();
    }

    public void save(Review review) {
        reviewDAO.save(review);
    }
}
