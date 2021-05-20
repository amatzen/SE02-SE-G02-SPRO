package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.models.Review;

import java.util.List;
import java.util.Optional;

/**
 * The interface Review controller.
 */
public interface IReviewController {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<Review> getAll();

    /**
     * Save.
     *
     * @param review the review
     */
    void save(Review review);

    /**
     * Update.
     *
     * @param review the review
     */
    void update(Review review);

    Optional<Review> getLatestReview(Programme programme);
}
