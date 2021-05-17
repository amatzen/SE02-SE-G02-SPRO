package dk.sdu.swe.views.partials;

import dk.sdu.swe.domain.models.CreditRole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.Objects;

public class CreditGroupListItem extends HBox {

    private CreditRole creditRole;

    @FXML
    private Label role;

    public CreditGroupListItem(CreditRole creditRole) {
        this.creditRole = creditRole;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/views/admin/components/CreditGroupListItem.fxml")));
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
        role.setText(creditRole.getTitle());
    }

}
