package dk.sdu.swe.views.partials;

import dk.sdu.swe.domain.controllers.CreditController;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.views.modals.credits.CreditModal;
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

public class CreditListItem extends HBox {

    private Credit credit;

    @FXML
    private Label nameLbl, roleLbl;

    @FXML
    private ImageView creditImageView;

    private FlowPane container;

    public CreditListItem(Credit credit, FlowPane container) {
        this.credit = credit;
        this.container = container;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/credits/components/CreditListItem.fxml")));
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
        Person p = credit.getPerson();

        creditImageView.setImage(new Image(p.getImage(), true));

        nameLbl.setText(p.getName());
        roleLbl.setText(credit.getRole().getTitle());
    }

    @FXML
    private void editBtn(ActionEvent event) {
        Dialog<Credit> editDialog = new CreditModal(getScene().getWindow(), credit, credit.getProgramme());
        Optional<Credit> credit = editDialog.showAndWait();
        credit.ifPresent(creditObj -> {
            CreditController.getInstance().update(creditObj);
        });
    }

    @FXML
    private void delete(ActionEvent event) {
        CreditController.getInstance().delete(credit);
        container.getChildren().remove(this);
    }
}
