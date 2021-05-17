package dk.sdu.swe.views.modals.companies;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.domain.controllers.CompanyController;
import dk.sdu.swe.domain.models.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class AddCompanyModal extends Dialog<Company> {

   @FXML
    private TextField companyName;

    @FXML
    private TextField cvrNumber;

    @FXML
    private TextField address;

    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXButton saveBtn;
    private GaussianBlur backgroundEffect;


    public AddCompanyModal(Window window) {
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
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/components/AddCompanyModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleClose(ActionEvent event) {
        getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        setResult(null);
        hide();
    }

    @FXML
    private void save(ActionEvent event) {
        String company = this.companyName.getText();
        String cvr = this.cvrNumber.getText();
        String address = this.address.getText();
        Company companyObj = CompanyController.getInstance().createCompany(company, cvr, address);
        setResult(companyObj);
        hide();
    }

}
