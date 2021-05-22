package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.models.Review;

import java.util.Optional;

/**
 * The interface Review dao.
 */
public interface IReviewDAO extends IDAO<Review> {
    Optional<Review> getLatestReview(Programme programme);
}
