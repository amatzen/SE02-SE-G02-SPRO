package dk.sdu.swe.presentation.controllers;

import dk.sdu.swe.domain.controllers.CreditRoleController;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.presentation.controllers.modals.credits.CreditGroupModal;
import dk.sdu.swe.presentation.controllers.partials.CreditGroupListItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Credit group view controller.
 */
public class CreditGroupViewController extends VBox {

    @FXML
    private ListView<CreditGroupListItem> creditRoles;

    /**
     * Instantiates a new Credit group view controller.
     */
    public CreditGroupViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/admin/components/CreditGroupView.fxml")));
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
        List<CreditRole> creditRoles = CreditRoleController.getInstance().getAll();
        for (CreditRole creditRole : creditRoles) {
            this.creditRoles.getItems().add(new CreditGroupListItem(creditRole, this.creditRoles));
        }
    }

    @FXML
    private void addCreditRole(ActionEvent event) {
        Dialog<CreditRole> creditRoleModal = new CreditGroupModal(getScene().getWindow());
        Optional<CreditRole> creditRole = creditRoleModal.showAndWait();
        creditRole.ifPresent(creditRoleObj -> {
            creditRoles.getItems().add(new CreditGroupListItem(creditRoleObj, creditRoles));
        });
    }
}
