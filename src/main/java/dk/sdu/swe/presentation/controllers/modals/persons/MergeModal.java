package dk.sdu.swe.presentation.controllers.modals.persons;

import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.domain.controllers.PersonController;
import dk.sdu.swe.domain.models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * The type Merge modal.
 */
public class MergeModal extends Dialog<Person> {

    @FXML
    private JFXComboBox<Label> creditRole, person;

    private GaussianBlur backgroundEffect;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    private Person personObj;

    /**
     * Instantiates a new Merge modal.
     *
     * @param window the window
     * @param person the person
     */
    public MergeModal(Window window, Person person) {
        this.personObj = person;

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
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/persons/components/MergeModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        image.setImage(new Image(personObj.getImage()));
        name.setText(personObj.getName());

        List<Person> personList = PersonController.getInstance().getAll();
        for (Person personObj : personList) {
            if (personObj.getId().equals(this.personObj.getId())) {
                continue;
            }
            Label label = new Label(personObj.getName());
            label.setUserData(personObj);
            person.getItems().add(label);
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
        try {
        Person personToMerge = (Person) this.person.getSelectionModel().getSelectedItem().getUserData();
        PersonController.getInstance().merge(this.personObj, personToMerge);

        setResult(personToMerge);
        hide();
        } catch (Exception ignored){ //Ignoring exceptions
        }
    }

}
