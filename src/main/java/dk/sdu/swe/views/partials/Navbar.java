package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.controllers.AuthController;
import dk.sdu.swe.views.Router;
import dk.sdu.swe.views.AdminViewController;
import dk.sdu.swe.views.CompanyViewController;
import dk.sdu.swe.views.PersonsViewController;
import dk.sdu.swe.views.ProgrammesViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class Navbar extends VBox {

    @FXML
    private JFXButton progBtn, companyBtn, pplBtn, adminBtn, profileBtn;

    @FXML
    private ImageView pfpImgView;

    private Router router;

    public Navbar(Router router) {
        this.router = router;

        FXMLLoader fxmlLoader = new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("dk/sdu/swe/ui/Navbar.fxml")));
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
        router.goTo(ProgrammesViewController.class);

        profileBtn.setText(AuthController.getInstance().getUser().getName().toString());

    }

    @FXML
    private void handle(ActionEvent e) {
        Object source = e.getSource();

        if (source == progBtn) {
            router.goTo(ProgrammesViewController.class);
        } else if (source == companyBtn) {
            router.goTo(CompanyViewController.class);
        } else if (source == pplBtn) {
            router.goTo(PersonsViewController.class);
        } else if (source == adminBtn) {
            router.goTo(AdminViewController.class);
        } else if (source == profileBtn) {

        }

    }

}
