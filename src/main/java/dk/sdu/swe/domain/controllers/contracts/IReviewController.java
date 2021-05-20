package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Review;

import java.util.List;

public interface IReviewController {
    List<Review> getAll();

    void save(Review review);

    void update(Review review);
}
