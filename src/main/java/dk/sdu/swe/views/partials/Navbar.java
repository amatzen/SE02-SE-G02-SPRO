package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import dk.sdu.swe.controllers.AuthController;
import dk.sdu.swe.views.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Navbar extends VBox {

    @FXML
    private JFXButton progBtn, companyBtn, pplBtn, adminBtn, profileBtn;

    @FXML
    private ImageView pfpImgView;

    private Map<String, Runnable> profileBtnOptions = Map.of(
            "Log out", () -> {
                try {
                    SceneNavigator.goTo("login", true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

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

        JFXButton[] btns = {progBtn,companyBtn,pplBtn,adminBtn};
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
            if(AuthController.getInstance().getUser().hasPermission(perm)) {
                btn.setVisible(true);
                btn.setManaged(true);
            }
        });

        profileBtn.setText(AuthController.getInstance().getUser().getName().toString());
    }

    @FXML
    private void handle(ActionEvent e) {
        switch (((JFXButton)e.getSource()).getId()) {
            case "progBtn" -> router.goTo(ProgrammesViewController.class);
            case "companyBtn" -> router.goTo(CompanyViewController.class);
            case "pplBtn" -> router.goTo(PersonsViewController.class);
            case "adminBtn" -> router.goTo(AdminViewController.class);
            case "profileBtn":
              JFXPopup popupListMenu = new PopupListMenu(profileBtnOptions);
              popupListMenu.show(profileBtn, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.RIGHT);
              break;
        }

    }

}
