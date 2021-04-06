package dk.sdu.swe.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private VBox content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            FXMLLoader loader = new FXMLLoader();
            content.getChildren().add(loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("dk/sdu/swe/ui/Nav.fxml"))));
            loader = new FXMLLoader();
            content.getChildren().add(loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/Company.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
