package dk.sdu.swe.views;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dk.sdu.swe.domain.controllers.CompanyController;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.views.modals.companies.CompanyModal;
import dk.sdu.swe.views.partials.CompanyListItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CompanyViewController extends BorderPane {

    @FXML
    private JFXListView companyListView;

    @FXML
    private JFXTextField searchField;

    public CompanyViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/views/companies/CompanyView.fxml")));
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
        Platform.runLater(() -> {
            new Thread(() -> {
                List<Company> companies = CompanyController.getInstance().getAll();
                updateCompanies(companies);
            }).start();
        });
    }

    private void updateCompanies(List<Company> companies) {
        companyListView.getItems().clear();
        for (Company company : companies) {
            Platform.runLater(() -> {
                companyListView.getItems().add(new CompanyListItem(company));
            });
        }
    }

    @FXML
    private void addCompanyBtn(ActionEvent event) {
        Dialog<Company> addCompanyModal = new CompanyModal(getScene().getWindow());
        Optional<Company> company = addCompanyModal.showAndWait();
        company.ifPresent(company1 -> {
            companyListView.getItems().add(new CompanyListItem(company1));
        });
    }

    @FXML
    private void search(ActionEvent event) {
        String searchTerm = searchField.getText();
        updateCompanies(CompanyController.getInstance().search(searchTerm));
    }

    @FXML
    private void resetSearch(ActionEvent event) {
        searchField.clear();
        updateCompanies(CompanyController.getInstance().getAll());
    }
}
