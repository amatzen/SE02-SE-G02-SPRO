package dk.sdu.swe.presentation.controllers.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import dk.sdu.swe.cross_cutting.helpers.Observer;
import dk.sdu.swe.cross_cutting.helpers.PubSub;
import dk.sdu.swe.domain.controllers.AuthController;
import dk.sdu.swe.presentation.Router;
import dk.sdu.swe.presentation.controllers.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Navbar.
 */
public class Navbar extends VBox implements Observer {

    @FXML
    private JFXButton progBtn, companyBtn, pplBtn, adminBtn, profileBtn;

    @FXML
    private ImageView pfpImgView;

    private Router router;

    private Map<String, Runnable> profileBtnOptions = new LinkedHashMap<>() {{
        put(AuthController.getInstance().getUser().getClass().getSimpleName(), () -> {
        });
        put("Log ud", () -> {
            AuthController.getInstance().logout();
            Router.getSceneRouter().goTo(AuthViewController.class);
        });
    }};

    /**
     * Instantiates a new Navbar.
     *
     * @param router the router
     */
    public Navbar(Router router) {
        PubSub.subscribe("routeChange", this);

        this.router = router;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/Navbar.fxml")));
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

        JFXButton[] btns = {progBtn, companyBtn, pplBtn, adminBtn};
        for (JFXButton btn : btns) {
            btn.setVisible(false);
            btn.setManaged(false);
        }

        (new HashMap<JFXButton, String>(Map.of(
            progBtn, "programmes",
            companyBtn, "companies",
            pplBtn, "people",
            adminBtn, "admin"
        ))).forEach((btn, perm) -> {
            if (AuthController.getInstance().getUser().hasPermission(perm)) {
                btn.setVisible(true);
                btn.setManaged(true);
            }
        });

        profileBtn.setText(AuthController.getInstance().getUser().getName().toString());
    }

    @FXML
    private void handle(ActionEvent e) {
        switch (((Button) e.getSource()).getId()) {
            case "progBtn" -> router.goTo(ProgrammesViewController.class);
            case "companyBtn" -> router.goTo(CompanyViewController.class);
            case "pplBtn" -> router.goTo(PersonsViewController.class);
            case "adminBtn" -> router.goTo(AdminViewController.class);
            case "profileBtn" -> {
                JFXPopup popupListMenu = new PopupListMenu(profileBtnOptions);
                popupListMenu.show(profileBtn, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.RIGHT);
            }
            default -> throw new IllegalStateException("Unexpected value: " + ((JFXButton) e.getSource()).getId());
        }

    }

    private void handleRouteChange(String routeName) {
        for (JFXButton jfxButton : (new JFXButton[]{
            progBtn,
            companyBtn,
            pplBtn,
            adminBtn
        })) {
            jfxButton.getStyleClass().remove("indicator");
        }

        switch (routeName) {
            case "ProgrammesViewController" -> this.progBtn.getStyleClass().add("indicator");
            case "CompanyViewController" -> this.companyBtn.getStyleClass().add("indicator");
            case "PersonsViewController" -> this.pplBtn.getStyleClass().add("indicator");
            case "AdminViewController",
                "ReviewViewController",
                "UserControlViewController",
                "CreditGroupViewController",
                "DataExportViewController" -> this.adminBtn.getStyleClass().add("indicator");
        }
    }

    @Override
    public void onNotify(String topic, Object payload) {
        switch (topic) {
            case "routeChange" -> handleRouteChange((String) payload);
        }
    }
}
