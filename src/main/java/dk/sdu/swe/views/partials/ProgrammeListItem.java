package dk.sdu.swe.views.partials;

import dk.sdu.swe.domain.controllers.ProgrammeController;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.views.modals.credits.CreditListModal;
import dk.sdu.swe.views.modals.programmes.ProgrammeModal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
                getClass().getClassLoader().getResource("dk/sdu/swe/views/programmes/ProgrammeListItem.fxml")));
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
        updateState();
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
        Dialog<Programme> programmeDialog = new ProgrammeModal(getScene().getWindow(), programme);
        Optional<Programme> programme = programmeDialog.showAndWait();
        programme.ifPresent(programmeObj -> {
            ProgrammeController.getInstance().updateProgramme(programmeObj);
            updateState();
        });
    }

}
