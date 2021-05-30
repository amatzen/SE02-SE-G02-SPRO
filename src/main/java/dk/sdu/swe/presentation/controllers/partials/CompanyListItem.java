package dk.sdu.swe.presentation.controllers.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import dk.sdu.swe.domain.controllers.CompanyController;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.presentation.controllers.modals.companies.CompanyModal;
import dk.sdu.swe.presentation.controllers.modals.companies.UserAdministrationModal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Company list item.
 */
public class CompanyListItem extends VBox {

    private Company company;
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
    private Map<String, Runnable> options = new LinkedHashMap<>() {{
        put("Brugeradministration", CompanyListItem.this::manageUsers);
        put("Rediger virksomhed", CompanyListItem.this::editCompany);
        put("Slet", CompanyListItem.this::deleteCompany);
    }};

    /**
     * Instantiates a new Company list item.
     *
     * @param company the company
     */
    public CompanyListItem(Company company) {
        this.company = company;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/companies/components/CompanyListItem.fxml")));
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
        if (company.getUsers().size() > 0) options.remove("Slet");
        PopupListMenu popupList = new PopupListMenu(options);

        actionsBtn.setOnMouseClicked(e -> {
            popupList.show(actionsBtn, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        });

        updateState();
    }

    private void updateState() {
        companyNameLabel.setText(company.getName());
        if (company.getCompanyDetails().getNbr().isEmpty()) {
            cvrLabel.setText("");
        } else {
            cvrLabel.setText("CVR: " + company.getCompanyDetails().getNbr());

        }
    }

    private void editCompany() {
        Dialog<Company> editCompanyDialog = new CompanyModal(getScene().getWindow(), company);
        Optional<Company> company = editCompanyDialog.showAndWait();

        company.ifPresent(company1 -> {
            CompanyController.getInstance().update(company1);
            updateState();
        });
    }

    private void deleteCompany() {
        // Midlertidigt indtil at dette er implementeret
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Slet virksomhed");
        alert.setHeaderText(null);
        alert.setContentText("Sletning af virksomheder er desværre ikke understøttet endnu.");
        alert.showAndWait();
    }

    private void manageUsers() {
        Dialog<Boolean> userAdministrationDialog = new UserAdministrationModal(getScene().getWindow(), company);
        userAdministrationDialog.show();
    }

}
