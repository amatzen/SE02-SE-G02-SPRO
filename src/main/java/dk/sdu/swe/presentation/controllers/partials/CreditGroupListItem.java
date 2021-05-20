package dk.sdu.swe.presentation.controllers.partials;

import com.jfoenix.controls.JFXPopup;
import dk.sdu.swe.domain.controllers.CreditRoleController;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.presentation.controllers.modals.credits.CreditGroupModal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Credit group list item.
 */
public class CreditGroupListItem extends HBox {

    private Map<String, Runnable> options = new LinkedHashMap<>() {{
        put("Rediger", CreditGroupListItem.this::editGroup);
        put("Slet", CreditGroupListItem.this::deleteGroup);
    }};

    private CreditRole creditRole;

    @FXML
    private Button actionsBtn;

    @FXML
    private Label role;

    private ListView<CreditGroupListItem> container;

    /**
     * Instantiates a new Credit group list item.
     *
     * @param creditRole the credit role
     * @param container  the container
     */
    public CreditGroupListItem(CreditRole creditRole, ListView<CreditGroupListItem> container) {
        this.creditRole = creditRole;
        this.container = container;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/admin/components/CreditGroupListItem.fxml")));
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

        actionsBtn.setOnMouseClicked(e -> {
            popupList.show(actionsBtn, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        });

        updateState();
    }

    private void updateState() {
        role.setText(creditRole.getTitle());
    }

    private void deleteGroup() {
        CreditRoleController.getInstance().delete(creditRole);
        container.getItems().remove(this);
    }

    private void editGroup() {
        Dialog<CreditRole> creditRoleModal = new CreditGroupModal(getScene().getWindow(), creditRole);
        Optional<CreditRole> creditRole = creditRoleModal.showAndWait();
        creditRole.ifPresent(creditRoleObj -> {
            CreditRoleController.getInstance().update(creditRoleObj);
            updateState();
        });
    }

}
