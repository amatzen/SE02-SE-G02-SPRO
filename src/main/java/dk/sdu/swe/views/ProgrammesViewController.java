package dk.sdu.swe.views;

import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.views.partials.ProgrammeListItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ProgrammesViewController extends BorderPane {

    @FXML
    private JFXListView<ProgrammeListItem> programmesListView;

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
        return null;
    }

    @FXML
    private void initialize() {
        List<Programme> programmeList = null;
        try {
            programmeList = getAllProgrammes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Programme programme : programmeList) {
            programmesListView.getItems().add(new ProgrammeListItem(programme));
        }

    }

}
