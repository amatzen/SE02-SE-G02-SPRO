package dk.sdu.swe.views.modals.companies;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.domain.models.Company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class EditCompanyDialog extends Dialog<Boolean> {

    @FXML
    private TextField companyName;

    @FXML
    private TextField cvrNumber;

    @FXML
    private TextField adresseField;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton closeBtn;

    @FXML
    private Company company;

    @FXML
    private GaussianBlur backgroundEffect;
    @FXML
    private JFXListView usersListView;


    public EditCompanyDialog(Window window, Company company) {
        this.company = company;

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
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/components/editCompanyModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        companyName.setText(company.getName());


    }

    @FXML
    void handleClose(ActionEvent event) {
        setResult(false);
        hide();
    }



}
