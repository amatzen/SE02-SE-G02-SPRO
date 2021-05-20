package dk.sdu.swe.presentation.controllers.modals.credits;

import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.domain.controllers.CreditController;
import dk.sdu.swe.domain.controllers.CreditRoleController;
import dk.sdu.swe.domain.controllers.PersonController;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * The type Credit modal.
 */
public class CreditModal extends Dialog<Credit> {

    @FXML
    private JFXComboBox<Label> creditRole, person;

    private GaussianBlur backgroundEffect;

    private Credit credit;

    @FXML
    private ImageView image;

    @FXML
    private TextField name;

    private Programme programme;

    /**
     * Instantiates a new Credit modal.
     *
     * @param window    the window
     * @param programme the programme
     */
    public CreditModal(Window window, Programme programme) {
        this(window, null, programme);
    }

    /**
     * Instantiates a new Credit modal.
     *
     * @param window    the window
     * @param credit    the credit
     * @param programme the programme
     */
    public CreditModal(Window window, Credit credit, Programme programme) {
        this.credit = credit;
        this.programme = programme;

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
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/credits/CreditModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        List<CreditRole> creditRoleList = CreditRoleController.getInstance().getAll();
        creditRoleList.forEach(creditRoleObj -> {
            Label label = new Label(creditRoleObj.getTitle());
            label.setUserData(creditRoleObj);
            creditRole.getItems().add(label);
            if (credit != null) {
                if (credit.getRole().getId().equals(creditRoleObj.getId())) {
                    creditRole.getSelectionModel().select(label);
                }
            }
        });

        List<Person> personList = PersonController.getInstance().getAll();
        personList.forEach(personObj -> {
            Label label = new Label(personObj.getName());
            label.setUserData(personObj);
            person.getItems().add(label);
            if (credit != null) {
                if (credit.getPerson().getId().equals(personObj.getId())) {
                    person.getSelectionModel().select(label);
                }
            }
        });

    }

    @FXML
    private void handleClose(ActionEvent event) {
        getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        setResult(null);
        hide();
    }

    @FXML
    private void save(ActionEvent event) {
        Person person = (Person) this.person.getSelectionModel().getSelectedItem().getUserData();
        CreditRole creditRole = (CreditRole) this.creditRole.getSelectionModel().getSelectedItem().getUserData();

        Credit credit;
        if (this.credit == null) {
            credit = CreditController.getInstance().createCredit(person, creditRole, programme);
        } else {
            this.credit.setPerson(person);
            this.credit.setRole(creditRole);
            credit = this.credit;
        }

        setResult(credit);
        hide();
    }

}
