package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.cross_cutting.helpers.PubSub;
import dk.sdu.swe.domain.controllers.contracts.IReviewController;
import dk.sdu.swe.domain.models.*;
import dk.sdu.swe.domain.persistence.IReviewDAO;
import dk.sdu.swe.persistence.dao.CategoryDAOImpl;
import dk.sdu.swe.persistence.dao.ReviewDAOImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

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

    public void acceptReviewOnProgramme(Review review) {
        Programme programme = review.getProgramme();
        JSONObject updated = review.getUpdated();

        JSONArray jsonCategories = updated.getJSONArray("categories");
        Set<Category> categories = new LinkedHashSet<>();
        for (int i = 0; i < jsonCategories.length(); i++) {
            int catId = (int) jsonCategories.get(i);
            Category category = CategoryDAOImpl.getInstance().getById(Long.parseLong(String.valueOf(catId))).orElse(null);
            if(!Objects.isNull(category)) {
                categories.add(category);
            }
        }

        programme.setTitle(updated.getString("title"));
        programme.setCategories(categories);

        Channel channel = ChannelController.getInstance().get(updated.getInt("channel")).orElse(null);

        if(Objects.nonNull(channel)) {
            programme.setChannel(channel);
        }

        programme.setProdYear(updated.getInt("prodYear"));
        review.setState(ReviewState.ACCEPTED);
        ProgrammeController.getInstance().update(programme);
        ReviewController.getInstance().update(review);
        PubSub.publish("trigger_refresh:admin:reviews", true);
    }


}
