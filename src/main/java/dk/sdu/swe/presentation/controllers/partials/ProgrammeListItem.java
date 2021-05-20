package dk.sdu.swe.presentation.controllers.partials;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.Application;
import dk.sdu.swe.domain.controllers.ProgrammeController;
import dk.sdu.swe.domain.controllers.ReviewController;
import dk.sdu.swe.domain.models.*;
import dk.sdu.swe.presentation.controllers.modals.credits.CreditListModal;
import dk.sdu.swe.presentation.controllers.modals.programmes.ProgrammeModal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Programme list item.
 */
public class ProgrammeListItem extends AnchorPane {

    @FXML
    private Label nameLbl, releaseYearLbl, categoryLbl;

    @FXML
    private JFXButton rs_draft, rs_awaiting, rs_complete;

    @FXML
    private ImageView rs_draft_img, rs_awaiting_img, rs_complete_img;

    @FXML
    private FlowPane channelsPane;

    private Programme programme;
    private Review review;

    /**
     * Instantiates a new Programme list item.
     *
     * @param programme the programme
     */
    public ProgrammeListItem(Programme programme) {
        this.programme = programme;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/programmes/ProgrammeListItem.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            checkReviewState();
            nameLbl.setText(programme.getTitle());
            categoryLbl.setText(programme.getCategories().stream()
                .map(Category::getCategoryTitle)
                .collect(Collectors.joining(", ")));
            releaseYearLbl.setText(String.valueOf(programme.getProdYear()));

            Channel channel = programme.getChannel();

            if (channel != null) {
                Image logo = new Image(channel.getLogo(), 48, 48, true, false, true);
                channelsPane.getChildren().clear();
                channelsPane.getChildren().add(new ImageView(logo));
            }
        });
    }

    private void checkReviewState() {
        review = ReviewController.getInstance().getLatestReview(programme).orElse(null);
        if(!Objects.nonNull(review)) {
            setReviewState(ReviewState.ACCEPTED);
        } else {
            setReviewState(review.getState());
        }
    }

    private Image getReviewStateButtonImg(String slug) {
        InputStream is = Application.class.getResourceAsStream("presentation/images/review_btn/"+slug+".png");
        return new Image(is);
    }

    private void setReviewState(ReviewState rs) {
        switch (rs) {
            case DRAFT -> {
                rs_draft_img.setImage(getReviewStateButtonImg("rs_draft_active"));
                rs_awaiting_img.setImage(getReviewStateButtonImg("rs_awaiting_default"));
                rs_complete_img.setImage(getReviewStateButtonImg("rs_complete_default"));
            }
            case AWAITING -> {
                rs_draft_img.setImage(getReviewStateButtonImg("rs_draft_default"));
                rs_awaiting_img.setImage(getReviewStateButtonImg("rs_awaiting_active"));
                rs_complete_img.setImage(getReviewStateButtonImg("rs_complete_default"));
            }
            default -> {
                rs_draft_img.setImage(getReviewStateButtonImg("rs_draft_default"));
                rs_awaiting_img.setImage(getReviewStateButtonImg("rs_awaiting_default"));
                rs_complete_img.setImage(getReviewStateButtonImg("rs_complete_active"));
            }
        }
    }

    private void updateState() {
        nameLbl.setText(programme.getTitle());
        categoryLbl.setText(programme.getCategories().stream()
            .map(Category::getCategoryTitle)
            .collect(Collectors.joining(", ")));
        releaseYearLbl.setText(String.valueOf(programme.getProdYear()));

        Channel channel = programme.getChannel();

        if (channel != null) {
            Image logo = new Image(channel.getLogo(), 48, 48, true, false, true);
            channelsPane.getChildren().clear();
            channelsPane.getChildren().add(new ImageView(logo));
        }
    }

    @FXML
    private void showCredits() {
        Dialog creditListModal = new CreditListModal(getScene().getWindow(), programme);
        creditListModal.show();
    }

    @FXML
    private void edit(ActionEvent event) {
        if(Objects.nonNull(review) && review.getState() == ReviewState.AWAITING) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Bekræft overskrivning");
            alert.setHeaderText("Vil du overskrive den afventende ændring?");
            alert.setContentText("Der er i forvejen en ændring på dette program som afventer godkendelse. Hvis du fortsætter, så sletter du den tidligere ændring.");

            ButtonType continueBtn = new ButtonType("Fortsæt");
            ButtonType cancelBtn = new ButtonType("Annuller");

            alert.getButtonTypes().setAll(continueBtn, cancelBtn);
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isEmpty() || result.get() != continueBtn) return;

            review.setState(ReviewState.DENIED);
            ReviewController.getInstance().update(review);
            updateState();
        }

        this.review = null;
        checkReviewState();

        Dialog<Programme> programmeDialog = new ProgrammeModal(getScene().getWindow(), programme);
        Optional<Programme> programme = programmeDialog.showAndWait();
        programme.ifPresent(programmeObj -> {
            ProgrammeController.getInstance().update(programmeObj);
            updateState();
        });
    }

}
