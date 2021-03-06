package dk.sdu.swe.presentation.controllers.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import dk.sdu.swe.cross_cutting.helpers.PubSub;
import dk.sdu.swe.domain.controllers.ReviewController;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.domain.models.ReviewState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

/**
 * The type Review list item.
 */
public class ReviewListItem extends VBox {
    /**
     * The Review.
     */
    Review review;

    @FXML
    private Label lbl_ProgramTitle, lbl_RequesterName, lbl_RequesterCompany;

    @FXML
    private JFXButton btn_ApproveReview, btn_DeclineReview;

    @FXML
    private JFXTextArea txt_Description;

    /**
     * Instantiates a new Review list item.
     *
     * @param review the review
     */
    public ReviewListItem(Review review) {
        this.review = review;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/admin/components/ReviewListItem.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        lbl_ProgramTitle.setText(review.getProgramme().getTitle());
        lbl_RequesterName.setText(review.getUser().getName().toString());
        lbl_RequesterCompany.setText(review.getUser().getCompany().getName());

        btn_ApproveReview.setOnAction(this::approve);
        btn_DeclineReview.setOnAction(this::decline);
    }

    private void approve(ActionEvent event) {
        ReviewController.getInstance().acceptReviewOnProgramme(review);
    }

    private void decline(ActionEvent event) {
        review.setState(ReviewState.DENIED);
        ReviewController.getInstance().update(review);
        PubSub.publish("trigger_refresh:admin:reviews", true);
    }
}
