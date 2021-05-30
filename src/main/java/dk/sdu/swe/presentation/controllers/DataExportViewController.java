package dk.sdu.swe.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import dk.sdu.swe.domain.controllers.ExportController;
import dk.sdu.swe.domain.controllers.contracts.IExportController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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

    private IExportController exportController;

    private String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

    /**
     * Instantiates a new Data export view controller.
     */
    public DataExportViewController() {
        exportController = ExportController.getInstance();

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
    private void export(ActionEvent event) {
        if (!creditData.isSelected() && !programData.isSelected() &&
            !companyData.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst data som skal eksporteres.");
            alert.showAndWait();
            return;
        } else if (!csvBtn.isSelected() && !jsonBtn.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst filtype.");
            alert.showAndWait();
            return;
        }

        if (csvBtn.isSelected()) {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Eksportmappe");

            exportController.exportCsv(Map.of(
                ExportController.ExportType.CREDITS, creditData.isSelected(),
                ExportController.ExportType.COMPANIES, companyData.isSelected(),
                ExportController.ExportType.PROGRAMMES, programData.isSelected()
            ), chooser.showDialog(getScene().getWindow()));
        }

        if (jsonBtn.isSelected()) {
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("CrMS Data - JSON");
            fileChooser.setInitialFileName("crms_" + date);
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"));

            exportController.exportJson(
                fileChooser.showSaveDialog(getScene().getWindow()),
                Map.of(
                    ExportController.ExportType.CREDITS, creditData.isSelected(),
                    ExportController.ExportType.COMPANIES, companyData.isSelected(),
                    ExportController.ExportType.PROGRAMMES, programData.isSelected()
                )
            );
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
