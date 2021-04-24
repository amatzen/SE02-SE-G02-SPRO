package dk.sdu.swe.views.partials;

import dk.sdu.swe.domain.models.Programme;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.Objects;

public class ProgrammeListItem extends AnchorPane {

    @FXML
    private Label nameLbl, releaseYearLbl, categoryLbl;

    private Programme programme;

    @FXML
    private FlowPane channelsPane;

    public ProgrammeListItem(Programme programme) {
        this.programme = programme;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/programmes/ProgrammeListItem.fxml")));
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
        nameLbl.setText(programme.getTitle());
        categoryLbl.setText(programme.getCategory());
        releaseYearLbl.setText(String.valueOf(programme.getProdYear()));
    }

}
