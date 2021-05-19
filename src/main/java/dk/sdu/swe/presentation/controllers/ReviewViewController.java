package dk.sdu.swe.presentation.controllers;

import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.domain.controllers.ReviewController;
import dk.sdu.swe.domain.models.Review;
import dk.sdu.swe.presentation.controllers.partials.ReviewListItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class ReviewViewController extends AnchorPane {

    @FXML
    private JFXListView<ReviewListItem> reviews;

    public ReviewViewController() {
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

    public void createListItem(Review review) throws IOException {
        reviews.getItems().add(new ReviewListItem(review));
    }

    @FXML
    private void initialize() {
        ReviewController.getInstance().getAll().forEach(x -> {
            System.out.println(x.toString());
            try {
                createListItem(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
