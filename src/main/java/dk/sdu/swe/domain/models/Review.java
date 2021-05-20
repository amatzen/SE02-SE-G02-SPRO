package dk.sdu.swe.domain.models;

import dk.sdu.swe.persistence.converters.JSONConverter;
import dk.sdu.swe.domain.controllers.AuthController;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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

    public Review() {
    }

    public Review(Programme programme, JSONObject original, JSONObject updated) {
        this.programme = programme;

        this.original = original;
        this.updated = updated;

        this.user = AuthController.getInstance().getUser();
        this.submission_time = ZonedDateTime.now(ZoneId.of("UTC"));

        this.state = ReviewState.AWAITING;
    }

    public void setState(ReviewState state) {
        this.state = state;
    }

    public ReviewState getState() {
        return state;
    }

    public ZonedDateTime getSubmission_time() {
        return submission_time;
    }

    public JSONObject getOriginal() {
        return original;
    }

    public JSONObject getUpdated() {
        return updated;
    }

    public Programme getProgramme() {
        return programme;
    }

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }
}

