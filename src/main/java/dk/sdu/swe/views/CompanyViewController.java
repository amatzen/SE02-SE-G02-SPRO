package dk.sdu.swe.views;

import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.views.modals.AddCompanyModal;
import dk.sdu.swe.views.partials.CompanyListItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CompanyViewController extends BorderPane {

    @FXML
    private JFXListView companyListView;

    public CompanyViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/CompanyView.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {/*
        List<Company> companies = null;
        try {
            companies = FacadeDB.getInstance().getCompanies();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Company company : companies) {
            companyListView.getItems().add(new CompanyListItem(company));
        }
        */
    }

    @FXML
    private void addCompanyBtn(ActionEvent event) {
        Dialog addCompanyModal = new AddCompanyModal();
        addCompanyModal.show();
    }
}
