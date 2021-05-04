package dk.sdu.swe.views;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.domain.controllers.ChannelController;
import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.controllers.ProgrammeController;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.views.modals.companies.AddCompanyModal;
import dk.sdu.swe.views.modals.persons.AddPersonModal;
import dk.sdu.swe.views.modals.programmes.AddProgrammesModal;
import dk.sdu.swe.views.partials.ProgrammeListItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class ProgrammesViewController extends BorderPane {

    @FXML
    private JFXListView<ProgrammeListItem> programmesListView;
    @FXML
    private JFXButton fabBtn;

    @FXML
    private JFXComboBox<Label> channels;

    @FXML
    private TextField searchField;

    @FXML
    private JFXComboBox<Label> categories;

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
        updateProgrammes(ProgrammeController.getInstance().getAll());

        List<Channel> channels = ChannelController.getInstance().getAll();

        for (Channel channel : channels) {
            Label label = new Label(channel.getName());
            label.setUserData(channel);
            this.channels.getItems().add(label);
        }

        List<String> categories = ProgrammeController.getInstance().getCategories();

        for (String category : categories) {
            this.categories.getItems().add(new Label(category));
        }
    }

    private void updateProgrammes(List<Programme> programmeList) {
        programmesListView.getItems().clear();
        for (Programme programme : programmeList) {
            programmesListView.getItems().add(new ProgrammeListItem(programme));
        }
    }

    @FXML
    private void onChannelChange(ActionEvent event) {
        search();
    }

    @FXML
    private void onCategoryChange(ActionEvent event) {
        search();
    }

    @FXML
    private void onSearchFieldAction(ActionEvent event) {
        search();
    }

    private void search() {
        Label channel = channels.getSelectionModel().getSelectedItem();
        Label category = categories.getSelectionModel().getSelectedItem();

        List<Programme> searchResult = ProgrammeController.getInstance().search(
            searchField.getText(),
            channel != null ? ((Channel) channel.getUserData()).getId() : null,
            category != null ? category.getText() : null);

        updateProgrammes(searchResult);
    }
    @FXML
    void addProgramme(ActionEvent event) {
        Dialog<Boolean> addProgrammesModal = new AddProgrammesModal(getScene().getWindow());
        addProgrammesModal.show();
    }


}
