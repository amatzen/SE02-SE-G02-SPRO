package dk.sdu.swe.presentation.controllers.modals.persons;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.cross_cutting.exceptions.PersonCreationException;
import dk.sdu.swe.domain.controllers.PersonController;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.presentation.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Person modal.
 */
public class PersonModal extends Dialog<Person> {

    @FXML
    private ImageView image;

    @FXML
    private JFXButton editBtn, saveBtn, closeBtn;

    @FXML
    private DatePicker bday;

    @FXML
    private TextField name, email, imageUrl;

    @FXML
    private JFXComboBox<Label> creditRole;

    private GaussianBlur backgroundEffect;

    private Person person;

    /**
     * Instantiates a new Person modal.
     *
     * @param window the window
     */
    public PersonModal(Window window) {
        this(window, null);
    }

    /**
     * Instantiates a new Person modal.
     *
     * @param window the window
     * @param person the person
     */
    public PersonModal(Window window, Person person) {
        this.person = person;

        this.setResultConverter(param -> null);
        this.initOwner(window);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);

        backgroundEffect = new GaussianBlur(10);
        window.getScene().getRoot().setEffect(backgroundEffect);

        setOnCloseRequest((event) -> {
            getOwner().getScene().getRoot().setEffect(null);
        });

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/persons/components/PersonModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {

        if (person != null) {
            email.setText(person.getEmail());
            name.setText(person.getName());
            bday.setValue(person.getZonedDate().toLocalDate());
            imageUrl.setText(person.getImage());
            image.setImage(new Image(person.getImage(), true));
        }

    }

    @FXML
    private void handleClose(ActionEvent event) {
        getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        setResult(null);
        hide();
    }

    @FXML
    private void save(ActionEvent event) {

        String name = this.name.getText();
        String email = this.email.getText();
        ZonedDateTime bday = getDate(this.bday).orElse(null);
        String image = imageUrl.getText().isBlank() ? null : imageUrl.getText();

        Person person = null;
        if (this.person == null) {
            try {
                person = PersonController.getInstance().createPerson(name, image, email, bday);
            } catch (PersonCreationException e) {
                AlertHelper.show(Alert.AlertType.ERROR, getOwner(), "Kunne ikke oprette person", e.getMessage());
                return;
            }
        } else {
            this.person.setName(name);
            this.person.setImage(image);
            this.person.setDateOfBirth(bday);
            this.person.putContactDetail("email", email);
            this.person.setImage(image);
            person = this.person;
        }
        setResult(person);
        hide();
    }

    private Optional<ZonedDateTime> getDate(DatePicker datePicker) {
        return datePicker.getValue() != null
            ? Optional.of(ZonedDateTime.of(datePicker.getValue().atTime(0, 0), ZoneId.of("UTC")))
            : Optional.empty();
    }

    @FXML
    private void loadImage(ActionEvent event) {
        image.setImage(new Image(imageUrl.getText(), true));
    }
}
