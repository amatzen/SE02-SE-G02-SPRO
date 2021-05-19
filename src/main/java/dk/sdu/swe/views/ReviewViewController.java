package dk.sdu.swe.views;

import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.domain.controllers.ReviewController;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.domain.models.ReviewState;
import dk.sdu.swe.helpers.Observer;
import dk.sdu.swe.helpers.PubSub;
import dk.sdu.swe.views.partials.ReviewListItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class ReviewViewController extends AnchorPane implements Observer {



    @FXML
    private JFXListView<ReviewListItem> reviews;

    public ReviewViewController() {
        PubSub.subscribe("trigger_refresh:admin:reviews", this);

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/views/admin/components/ReviewView.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createListItem(Review review) throws IOException {
        reviews.getItems().add(new ReviewListItem(review));
    }

    @FXML
    private void initialize() {
        ReviewController.getInstance().getAll().stream().filter(x -> x.getState() == ReviewState.AWAITING).forEach(x -> {
            try {
                createListItem(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onNotify(String topic, Object payload) {
        review
    }
}
