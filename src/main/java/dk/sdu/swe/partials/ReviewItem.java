package dk.sdu.swe.partials;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;

public class ReviewItem {

    public ReviewItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("dk/sdu/swe/ui/admin/components/review.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
