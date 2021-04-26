package dk.sdu.swe.views.partials;

import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

public class CreditListItem extends HBox {

    private Credit credit;

    @FXML
    private Label nameLbl, roleLbl;

    @FXML
    private ImageView creditImageView;

    public CreditListItem(Credit credit) {
        this.credit = credit;

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

        Person p = null;
        try {
            p = Person.get(credit.getPerson());
        } catch (Exception e) {
            e.printStackTrace();
        }
        creditImageView.setImage(new Image(p.getImage(), true));

        nameLbl.setText(p.getName());

    }

}
