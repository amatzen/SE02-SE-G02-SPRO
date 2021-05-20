package dk.sdu.swe.presentation.controllers.partials;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;

/**
 * The type Review item.
 */
public class ReviewItem {

    /**
     * Instantiates a new Review item.
     */
    public ReviewItem() {
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

}
