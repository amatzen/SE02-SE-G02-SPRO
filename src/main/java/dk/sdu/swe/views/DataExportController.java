package dk.sdu.swe.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import dk.sdu.swe.domain.models.CsvExport;
import dk.sdu.swe.domain.models.JsonExport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.util.Objects;

public class DataExportController extends VBox {

    @FXML
    private JFXCheckBox creditData, programData, companyData;

    @FXML
    private JFXRadioButton csvBtn, jsonBtn;

    @FXML
    private JFXButton exportBtn;


    public DataExportController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/admin/components/exportBar.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exportCsv(ActionEvent event) {
        if (csvBtn.isSelected() == false && jsonBtn.isSelected() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst en filtype");
            alert.showAndWait();
        }
        if (creditData.isSelected() == false && programData.isSelected() == false &&
            companyData.isSelected() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst eksport data");
            alert.showAndWait();
        }
        if (csvBtn.isSelected() && creditData.isSelected()) {
            CsvExport.csvExportActors(new FileChooser().showSaveDialog(getScene().getWindow()));
        }
        if (csvBtn.isSelected() && programData.isSelected()) {
            CsvExport.csvExportPrograms();
        }
        if (csvBtn.isSelected() && companyData.isSelected()) {
            CsvExport.csvExportCompanies();
        }
        if (jsonBtn.isSelected() && creditData.isSelected()) {
            JsonExport.JsonExportActors();
        }
        if (jsonBtn.isSelected() && programData.isSelected()) {
            JsonExport.JsonExportPrograms();
        }
        if (jsonBtn.isSelected() && companyData.isSelected()) {
            JsonExport.JsonExportCompanies();
        }
    }

}
