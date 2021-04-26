package dk.sdu.swe.views;

import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.data.FacadeDB;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.views.partials.PersonListItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PersonsViewController extends VBox {

    @FXML
    private JFXListView peopleListView;

    public PersonsViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/persons/persons.fxml")));
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
        List<Person> people = null;
        try {
            people = FacadeDB.getInstance().getPeople();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Person person : people) {
            peopleListView.getItems().add(new PersonListItem(person));
        }
    }

    @FXML
    private void addPersonBtn(ActionEvent event) {

    }
}
