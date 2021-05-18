package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.ReviewDAOImpl;
import dk.sdu.swe.domain.models.Review;

import java.util.List;

public class ReviewController {

    private static ReviewController ReviewControllerInstance;

    private ReviewController() {
    }

    public static synchronized ReviewController getInstance() {
        if (ReviewControllerInstance == null) {
            ReviewControllerInstance = new ReviewController();
        }
        return ReviewControllerInstance;
    }

    public List<Review> getAll() {
        return ReviewDAOImpl.getInstance().getAll();
    }
}