package dk.sdu.swe.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import dk.sdu.swe.domain.models.CsvExport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * The type Data export view controller.
 */
public class DataExportViewController extends VBox {

    @FXML
    private JFXCheckBox creditData, programData, companyData;

    @FXML
    private JFXRadioButton csvBtn, jsonBtn;

    @FXML
    private JFXButton exportBtn;

    private String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

    /**
     * Instantiates a new Data export view controller.
     */
    public DataExportViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/admin/components/DataExportView.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Export(ActionEvent event) {

        if (creditData.isSelected() == false && programData.isSelected() == false &&
            companyData.isSelected() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst data som skal eksporteres.");
            alert.showAndWait();
        }
        else if (csvBtn.isSelected() == false && jsonBtn.isSelected() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst filtype.");
            alert.showAndWait();
        }

        // Midlertidigt indtil JSON eksportering er færdigt
        else if (jsonBtn.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("JSON eksportering");
            alert.setHeaderText(null);
            alert.setContentText("Eksportering til JSON er desværre ikke understøttet endnu.");
            alert.showAndWait();
        }

        FileChooser fileChooser = new FileChooser();
        if (csvBtn.isSelected() && creditData.isSelected()) {
            fileChooser.setTitle("Krediteringer - CSV");
            fileChooser.setInitialFileName("Krediteringer " + date);
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"));
            CsvExport.csvExportCredits(fileChooser.showSaveDialog(getScene().getWindow()));
        }
        if (csvBtn.isSelected() && programData.isSelected()) {
            fileChooser.setTitle("Programmer - CSV");
            fileChooser.setInitialFileName("Programmer " + date);
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
            CsvExport.csvExportPrograms(fileChooser.showSaveDialog(getScene().getWindow()));
        }
        if (csvBtn.isSelected() && companyData.isSelected()) {
            fileChooser.setTitle("Virksomheder - CSV");
            fileChooser.setInitialFileName("Virksomheder " + date);
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
            CsvExport.csvExportCompanies(fileChooser.showSaveDialog(getScene().getWindow()));
        }
        /*
        if (jsonBtn.isSelected() && creditData.isSelected()) {
            fileChooser.setTitle("Krediteringer - JSON");
            fileChooser.setInitialFileName("Krediteringer " + date);
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"));
            JsonExport.JsonExportCredits(fileChooser.showSaveDialog(getScene().getWindow()));
        }
        if (jsonBtn.isSelected() && programData.isSelected()) {
            fileChooser.setTitle("Programmer - JSON");
            fileChooser.setInitialFileName("Programmer " + date);
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"));
            JsonExport.JsonExportPrograms(fileChooser.showSaveDialog(getScene().getWindow()));
        }
        if (jsonBtn.isSelected() && companyData.isSelected()) {
            fileChooser.setTitle("Virksomheder - JSON");
            fileChooser.setInitialFileName("Virksomheder " + date);
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"));
            JsonExport.JsonExportCompanies(fileChooser.showSaveDialog(getScene().getWindow()));
        }
        */
    }

}
