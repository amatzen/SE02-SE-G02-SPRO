package dk.sdu.swe.views;

import dk.sdu.swe.views.partials.CompanyListItem;
import dk.sdu.swe.views.partials.NavBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AppViewController implements Initializable {

    @FXML
    private VBox content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        /*FXMLLoader loader = new FXMLLoader();
        content.getChildren().add(loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("dk/sdu/swe/ui/Nav.fxml"))));
        loader = new FXMLLoader();
        content.getChildren().add(loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/Main.fxml"))));*/
        content.getChildren().add(new NavBar());
        content.getChildren().add(new CompanyListItem());
    }

}
