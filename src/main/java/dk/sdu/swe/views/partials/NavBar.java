package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class NavBar extends VBox {

    @FXML
    private JFXButton progBtn, companyBtn, pplBtn, adminBtn, profileBtn;

    @FXML
    private ImageView pfpImgView;

    public NavBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("dk/sdu/swe/ui/Nav.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handle(ActionEvent e) {
        System.out.println("Test");
    }

}
