package dk.sdu.swe.partials;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.views.Router;
import dk.sdu.swe.views.AdminView;
import dk.sdu.swe.views.CompanyView;
import dk.sdu.swe.views.PersonsView;
import dk.sdu.swe.views.ProgrammesView;
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
        router.goTo(CompanyView.class);
    }

    @FXML
    private void handle(ActionEvent e) {
        Object source = e.getSource();

        if (source == progBtn) {
            router.goTo(ProgrammesView.class);
        } else if (source == companyBtn) {
            router.goTo(CompanyView.class);
        } else if (source == pplBtn) {
            router.goTo(PersonsView.class);
        } else if (source == adminBtn) {
            router.goTo(AdminView.class);
        } else if (source == profileBtn) {

        }

    }

}
