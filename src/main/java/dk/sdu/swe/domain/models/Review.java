package dk.sdu.swe.domain.models;

import dk.sdu.swe.data.converters.JSONConverter;
import dk.sdu.swe.domain.controllers.AuthController;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
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
    private JSONObject newProgramme;

    private ReviewState state;

    public Review() {
    }

    public Review(Programme programme, JSONObject newProgramme) {
        this.programme = programme;
        this.user = AuthController.getInstance().getUser();
        this.submission_time = ZonedDateTime.now(ZoneId.of("UTC"));
        this.newProgramme = newProgramme;

        this.state = ReviewState.AWAITING;
    }

    public void setState(ReviewState state) {
        this.state = state;
    }

    public ReviewState getState() {
        return state;
    }

    public Programme getProgramme() {
        return programme;
    }

    public ZonedDateTime getSubmission_time() {
        return submission_time;
    }

    public JSONObject getNewProgramme() {
        return newProgramme;
    }

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }
}

enum ReviewState {
    AWAITING,
    ACCEPTED,
    DENIED
}
