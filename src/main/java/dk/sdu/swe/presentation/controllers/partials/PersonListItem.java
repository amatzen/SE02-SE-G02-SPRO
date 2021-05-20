package dk.sdu.swe.presentation.controllers.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import dk.sdu.swe.domain.controllers.PersonController;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.presentation.controllers.modals.persons.MergeModal;
import dk.sdu.swe.presentation.controllers.modals.persons.PersonModal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Person list item.
 */
public class PersonListItem extends VBox {

    private Map<String, Runnable> options = new LinkedHashMap<>() {{
        put("Rediger", PersonListItem.this::editPerson);
        put("Slet", PersonListItem.this::deletePerson);
        put("Flet", PersonListItem.this::mergePerson);
    }};

    private Person person;

    @FXML
    private Label nameLbl, dobLbl, emailLbl;

    @FXML
    private ImageView personImageView;

    @FXML
    private JFXButton actionBtn;

    private ListView<PersonListItem> container;

    /**
     * Instantiates a new Person list item.
     *
     * @param person    the person
     * @param container the container
     */
    public PersonListItem(Person person, ListView<PersonListItem> container) {
        this.person = person;
        this.container = container;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/persons/components/PersonListItem.fxml")));
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
        PopupListMenu popupList = new PopupListMenu(options);

        actionBtn.setOnMouseClicked(e -> {
            popupList.show(actionBtn, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        });

        updateState();
    }

    private void updateState() {
        nameLbl.setText(person.getName());
        if (person.getZonedDate() != null) {
            dobLbl.setText(person.getZonedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        emailLbl.setText(person.getContactDetail("email"));
        personImageView.setImage(new Image(person.getImage(), true));
    }

    private void editPerson(){
        Dialog<Person> personEditDialog = new PersonModal(getScene().getWindow(), person);
        Optional<Person> person = personEditDialog.showAndWait();
        person.ifPresent(personObj -> {
            PersonController.getInstance().update(personObj);
            updateState();
        });
    }

    private void deletePerson(){
        container.getItems().remove(this);
        PersonController.getInstance().delete(person);
    }

    private void mergePerson() {
        Dialog<Person> mergeModal = new MergeModal(getScene().getWindow(), person);
        Optional<Person> mergedPerson = mergeModal.showAndWait();
        mergedPerson.ifPresent(personToRemove -> {
            container.getItems().removeIf(personListItem -> {
                return personListItem.getPerson().getId().equals(personToRemove.getId());
            });
        });
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }
}
