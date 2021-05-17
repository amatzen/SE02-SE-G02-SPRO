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
import java.io.IOException;
import java.util.Objects;

public class DataExportController extends VBox {

    @FXML
    private JFXCheckBox actorData, programData, companyData;

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
        if (csvBtn.isSelected()) {
            CsvExport.csvExport();
        }
        if (jsonBtn.isSelected()) {
            JsonExport.JsonExport();
        }
        else if (csvBtn.isSelected() == false && jsonBtn.isSelected() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl!");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst en filtype");
            alert.showAndWait();
        }

    }

}
