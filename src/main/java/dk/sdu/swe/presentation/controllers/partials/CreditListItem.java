package dk.sdu.swe.presentation.controllers.partials;

import dk.sdu.swe.domain.controllers.CreditController;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.presentation.controllers.modals.credits.CreditListModal;
import dk.sdu.swe.presentation.controllers.modals.credits.CreditModal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Credit list item.
 */
public class CreditListItem extends HBox {

    private Credit credit;

    @FXML
    private Label nameLbl, roleLbl;

    @FXML
    private ImageView creditImageView;

    private FlowPane container;
    private Runnable callback;

    /**
     * Instantiates a new Credit list item.
     *
     * @param credit    the credit
     * @param container the container
     */
    public CreditListItem(Credit credit, FlowPane container) {
        this.credit = credit;
        this.container = container;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/credits/components/CreditListItem.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CreditListItem(Runnable callback, Credit credit, FlowPane container) {
        this(credit, container);
        this.callback = callback;
    }

    @FXML
    private void initialize() {
        updateState();
    }

    private void updateState() {
        Person p = credit.getPerson();

        creditImageView.setImage(new Image(p.getImage(), true));

        nameLbl.setText(p.getName());
        roleLbl.setText(credit.getRole().getTitle());
    }

    @FXML
    private void editBtn(ActionEvent event) {
        Dialog<Credit> creditModal = new CreditModal(getScene().getWindow(), credit, credit.getProgramme());
        Optional<Credit> credit = creditModal.showAndWait();
        credit.ifPresent(creditObj -> {
            CreditController.getInstance().update(creditObj);
            updateState();
        });
    }

    @FXML
    private void delete(ActionEvent event) {
        CreditController.getInstance().delete(credit);
        container.getChildren().remove(this);
    }
}
