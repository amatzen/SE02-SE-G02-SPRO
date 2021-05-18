package dk.sdu.swe.views.modals.credits;

import dk.sdu.swe.data.dao.UserDAOImpl;
import dk.sdu.swe.domain.controllers.CreditRoleController;
import dk.sdu.swe.domain.controllers.UserController;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.IUserDAO;
import dk.sdu.swe.exceptions.UserCreationException;
import dk.sdu.swe.views.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class CreditRoleModal extends Dialog<CreditRole> {

    private IUserDAO userDAO;

    private Company company;

    @FXML
    private TextField creditRole;

    private CreditRole creditRoleObj;

    private GaussianBlur backgroundEffect;

    public CreditRoleModal(Window window) {
        this(window, null);
    }

    public CreditRoleModal(Window window, CreditRole creditRole) {
        userDAO = UserDAOImpl.getInstance();
        this.creditRoleObj = creditRole;

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
                getClass().getClassLoader().getResource("dk/sdu/swe/views/credits/CreditRoleModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        if (this.creditRoleObj != null) {
            creditRole.setText(this.creditRoleObj.getTitle());
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
        String role = this.creditRole.getText();

        CreditRole creditRole = null;
        if (this.creditRoleObj == null) {
             creditRole = CreditRoleController.getInstance().createRole(role);
        } else {
            this.creditRoleObj.setTitle(role);
            creditRole = this.creditRoleObj;
        }
        setResult(creditRole);
        hide();
    }

}
