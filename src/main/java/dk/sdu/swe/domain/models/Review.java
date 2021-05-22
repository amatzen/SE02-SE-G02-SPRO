package dk.sdu.swe.domain.models;

import dk.sdu.swe.domain.controllers.AuthController;
import dk.sdu.swe.persistence.converters.JSONConverter;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * The type Review.
 */
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Programme programme;

    @ManyToOne
    private User user;

    private ZonedDateTime submission_time;

    @Convert(converter = JSONConverter.class)
    private JSONObject original;

    @Convert(converter = JSONConverter.class)
    private JSONObject updated;

    private ReviewState state;

    /**
     * Instantiates a new Review.
     */
    public Review() {
    }

    /**
     * Instantiates a new Review.
     *
     * @param programme the programme
     * @param original  the original
     * @param updated   the updated
     */
    public Review(Programme programme, JSONObject original, JSONObject updated) {
        this.programme = programme;

        this.original = original;
        this.updated = updated;

        this.user = AuthController.getInstance().getUser();
        this.submission_time = ZonedDateTime.now(ZoneId.of("UTC"));

        this.state = ReviewState.AWAITING;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public ReviewState getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(ReviewState state) {
        this.state = state;
    }

    /**
     * Gets submission time.
     *
     * @return the submission time
     */
    public ZonedDateTime getSubmission_time() {
        return submission_time;
    }

    /**
     * Gets original.
     *
     * @return the original
     */
    public JSONObject getOriginal() {
        return original;
    }

    /**
     * Gets updated.
     *
     * @return the updated
     */
    public JSONObject getUpdated() {
        return updated;
    }

    /**
     * Gets programme.
     *
     * @return the programme
     */
    public Programme getProgramme() {
        return programme;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }
}

