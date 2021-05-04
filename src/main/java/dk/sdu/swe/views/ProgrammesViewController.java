package dk.sdu.swe.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.controllers.ProgrammeController;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.views.modals.companies.AddCompanyModal;
import dk.sdu.swe.views.modals.persons.AddPersonModal;
import dk.sdu.swe.views.modals.programmes.AddProgrammesModal;
import dk.sdu.swe.views.partials.ProgrammeListItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ProgrammesViewController extends BorderPane {

    @FXML
    private JFXListView<ProgrammeListItem> programmesListView;
    @FXML
    private JFXButton fabBtn;

    public ProgrammesViewController() {

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/programmes/ProgrammesView.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Programme> getAllProgrammes() {
        return DB.loadAllData(Programme.class, DB.openSession());
    }

    @FXML
    private void initialize() {
        List<Programme> programmeList = ProgrammeController.getInstance().getAll();

        for (Programme programme : programmeList) {
            programmesListView.getItems().add(new ProgrammeListItem(programme));
        }
    }

    @FXML
    void addProgramme(ActionEvent event) {
        Dialog<Boolean> addProgrammesModal = new AddProgrammesModal(getScene().getWindow());
        addProgrammesModal.show();
    }


}
