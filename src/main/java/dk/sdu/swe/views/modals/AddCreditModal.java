package dk.sdu.swe.views.modals;

import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.domain.controllers.CreditRoleController;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.CreditRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AddCreditModal extends Dialog<Boolean> {

    @FXML
    private JFXComboBox<Label> creditRole;

    private GaussianBlur backgroundEffect;

    private Credit credit;

    public AddCreditModal(Window window) {
        this(window, null);
    }

    public AddCreditModal(Window window, Credit credit) {
        this.credit = credit;

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
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/credits/AddCreditModal.fxml")));
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
                if (credit.getRole().getTitle().equals(creditRoleObj.getTitle())) {
                    creditRole.getSelectionModel().select(label);
                }
            }
        });

    }

    @FXML
    private void handleClose(ActionEvent event) {
        setResult(false);
        hide();
    }
}
