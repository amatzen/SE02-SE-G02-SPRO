package dk.sdu.swe.views;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.helpers.Observer;
import dk.sdu.swe.helpers.PubSub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class AdminViewController extends BorderPane implements Observer {

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
        PubSub.subscribe("routeChange", this);
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/views/admin/AdminView.fxml")));
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
            router.goTo(CreditGroupViewController.class);

        } else if (source==dataExportBtn){
            router.goTo(DataExportController.class);

        }

    }

    private void handleRouteChange(String routeName) {
        for (JFXButton jfxButton : (new JFXButton[]{
            userControlBtn,
            creditGroupBtn,
            dataExportBtn,
            reviewsBtn
        })) {
            jfxButton.getStyleClass().remove("indicator");
        }
        switch (routeName) {
            case "ReviewViewController" -> this.reviewsBtn.getStyleClass().add("indicator");
            case "UserControlController" -> this.userControlBtn.getStyleClass().add("indicator");
            case "CreditGroupController" -> this.creditGroupBtn.getStyleClass().add("indicator");
            case "DataExportController" -> this.dataExportBtn.getStyleClass().add("indicator");

        }
    }

    @Override
    public void onNotify(String topic, Object payload) {
        switch (topic) {
            case "routeChange" -> handleRouteChange((String) payload);
        }
    }
}
