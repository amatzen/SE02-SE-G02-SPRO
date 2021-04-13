package dk.sdu.swe.views;

import com.jfoenix.controls.JFXListView;
import dk.sdu.swe.partials.CompanyListItem;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class CompanyView extends BorderPane {

    @FXML
    private JFXListView<CompanyListItem> companyListView;

    public CompanyView() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/Main.fxml")));
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
            for (int i = 0; i < 16; i++) {
                companyListView.getItems().add(new CompanyListItem());
            }
        });
    }

}
