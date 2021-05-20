package dk.sdu.swe.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.cross_cutting.helpers.Observer;
import dk.sdu.swe.cross_cutting.helpers.PubSub;
import dk.sdu.swe.presentation.Router;
import javafx.application.Platform;
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
        Platform.runLater(() -> router.goTo(ReviewViewController.class));
    }

    private static volatile AdminViewController instance = null;

    public static Router getRouter() {
        return instance.router;
    }

    public AdminViewController() {
        if(instance == null) {
            instance = this;
        }

        PubSub.subscribe("routeChange", this);
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/admin/AdminView.fxml")));
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

        if (dataExportBtn.equals(source)) {
            router.goTo(DataExportViewController.class);
        } else if (userControlBtn.equals(source)) {
            router.goTo(UserControlViewController.class);
        } else if (creditGroupBtn.equals(source)) {
            router.goTo(CreditGroupViewController.class);
        } else {
            router.goTo(ReviewViewController.class);
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
            case "UserControlViewController" -> this.userControlBtn.getStyleClass().add("indicator");
            case "CreditGroupViewController" -> this.creditGroupBtn.getStyleClass().add("indicator");
            case "DataExportViewController"  ->  this.dataExportBtn.getStyleClass().add("indicator");
            default                          ->     this.reviewsBtn.getStyleClass().add("indicator");
        }
    }

    @Override
    public void onNotify(String topic, Object payload) {
        switch (topic) {
            case "routeChange" -> handleRouteChange((String) payload);
        }
    }
}
