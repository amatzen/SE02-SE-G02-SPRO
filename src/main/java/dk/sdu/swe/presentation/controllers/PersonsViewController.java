package dk.sdu.swe.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dk.sdu.swe.domain.controllers.PersonController;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.cross_cutting.helpers.Observer;
import dk.sdu.swe.cross_cutting.helpers.PubSub;
import dk.sdu.swe.presentation.controllers.modals.persons.PersonModal;
import dk.sdu.swe.presentation.controllers.partials.PersonListItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Persons view controller.
 */
public class PersonsViewController extends VBox implements Observer {

    @FXML
    private JFXListView peopleListView;

    @FXML
    private JFXButton fabBtn;

    @FXML
    private JFXTextField searchField;

    /**
     * Instantiates a new Persons view controller.
     */
    public PersonsViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/persons/PersonView.fxml")));
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
        PubSub.subscribe("trigger_update:person:refresh", this);
        Platform.runLater(() -> {
            new Thread(() -> {
                List<Person> people = PersonController.getInstance().getAll();
                updatePeople(people);
            }).start();
        });
    }

    private void updatePeople(List<Person> people) {
        peopleListView.getItems().clear();
        for (Person person : people) {
            peopleListView.getItems().add(new PersonListItem(person, peopleListView));
        }
    }

    @FXML
    private void addPersonModal(ActionEvent event) {
        Dialog<Person> addPerson = new PersonModal(getScene().getWindow());
        Optional<Person> person = addPerson.showAndWait();
        person.ifPresent(personObj -> {
            peopleListView.getItems().add(new PersonListItem(personObj, peopleListView));
        });
    }

    @FXML
    private void search(ActionEvent event) {
        String searchTerm = searchField.getText();
        updatePeople(PersonController.getInstance().search(searchTerm));
    }

    @FXML
    private void resetSearch(ActionEvent event) {
        searchField.clear();
        updatePeople(PersonController.getInstance().getAll());
    }

    @Override
    public void onNotify(String topic, Object payload) {
        updatePeople(PersonController.getInstance().getAll());
    }
}
