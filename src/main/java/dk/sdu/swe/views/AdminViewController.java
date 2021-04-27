package dk.sdu.swe.views;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class AdminViewController extends BorderPane {

    @FXML
    private JFXButton userControlBtn, creditGroupBtn, dataExportBtn, reviewsBtn;

    @FXML
    private Router router;

    @FXML
    private Pane contentPane;

    @FXML
    private void initialize() {
        router = new Router(contentPane);
    }

    public AdminViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/admin/admin.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSelectedSection(ActionEvent e) {

        Object source = e.getSource();

        if (source==reviewsBtn){
            router.goTo(ReviewViewController.class);

        }
        else if (source==userControlBtn){
            router.goTo(UserControlController.class);
        }
        else if (source==creditGroupBtn){
            router.goTo(CreditGroupController.class);

        } else if (source==dataExportBtn){
            router.goTo(DataExportController.class);

        }

    }

}
