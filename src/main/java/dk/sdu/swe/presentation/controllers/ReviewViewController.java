package dk.sdu.swe.presentation.controllers;

import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.cross_cutting.helpers.Observer;
import dk.sdu.swe.cross_cutting.helpers.PubSub;
import dk.sdu.swe.domain.controllers.ReviewController;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.domain.models.ReviewState;
import dk.sdu.swe.presentation.controllers.partials.ReviewListItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReviewViewController extends AnchorPane implements Observer {

    private List<Review> reviews_ = new ArrayList<>();

    @FXML
    private JFXListView<ReviewListItem> reviews;

    public ReviewViewController() {
        PubSub.subscribe("trigger_refresh:admin:reviews", this);

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/admin/components/ReviewView.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createListItem(Review review) throws IOException {
        reviews.getItems().add(new ReviewListItem(review));
    }

    private void clear() {
        reviews.getItems().clear();
    }

    @FXML
    private void initialize() {
        ReviewController.getInstance().getAll().stream().filter(x -> x.getState() == ReviewState.AWAITING).forEach(x -> {
            reviews_.add(x);
            try {
                createListItem(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onNotify(String topic, Object payload) {
        clear();
        for (Review r: reviews_) {
            try {
                createListItem(r);
            } catch (IOException e) {
            }
        }
    }
}
