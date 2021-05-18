package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Review;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReviewListItem extends HBox implements Initializable {
    Review review;

    @FXML
    private Label lbl_ProgramTitle, lbl_RequesterName, lbl_RequesterCompany;

    @FXML
    private JFXButton btn_ApproveReview, btn_DeclineReview;

    @FXML
    private JFXTextArea txt_Description;

    public ReviewListItem(Review review) {
        this.review = review;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/views/admin/components/ReviewListItem.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_ProgramTitle.setText(review.getProgramme().getTitle());
        lbl_RequesterName.setText(review.getUser().getName().toString());
        lbl_RequesterCompany.setText(review.getUser().getCompany().getName());
    }
}
