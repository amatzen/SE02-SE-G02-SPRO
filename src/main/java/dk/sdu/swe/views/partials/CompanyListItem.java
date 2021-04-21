package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import dk.sdu.swe.views.modals.EditProgrammeDialog;
import dk.sdu.swe.views.modals.UserAdministrationDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CompanyListItem extends VBox {

    private Map<String, Runnable> options = new LinkedHashMap<>() {{
        put("Håndtér brugere", CompanyListItem.this::manageUsers);
        put("Rediger", CompanyListItem.this::editCompany);
        put("Slet", CompanyListItem.this::deleteCompany);
    }};


    @FXML
    private JFXButton actionsBtn;

    @FXML
    private Label cvrLabel;

    @FXML
    private Label ceoLabel;

    @FXML
    private Label adminLabel;

    @FXML
    private Label companyNameLabel;

    public CompanyListItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/components/CompanyListItem.fxml")));
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
    }

    private void editCompany() {

    }

    private void deleteCompany() {
        System.out.println("Test2");
    }

    private void manageUsers() {
        Dialog<Boolean> userAdministrationDialog = new UserAdministrationDialog(getScene().getWindow());
        userAdministrationDialog.show();
    }

}
