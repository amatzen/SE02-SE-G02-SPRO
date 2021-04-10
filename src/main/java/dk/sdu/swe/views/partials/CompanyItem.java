package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.skins.JFXListViewSkin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.checkerframework.checker.initialization.qual.Initialized;

import java.io.IOException;
import java.util.Objects;

public class CompanyItem extends VBox {

    @FXML
    private JFXButton actionsBtn;

    @FXML
    private Label cvrLabel;

    @FXML
    private Label ceoLabel;

    @FXML
    private Label adminLabel;

    public CompanyItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/CompanyItem.fxml")));
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
        JFXListView<Label> list = new JFXListView<>();
        for (int i = 1; i < 5; i++) {
            list.getItems().add(new Label("Item" + i));
        }

        JFXPopup popup = new JFXPopup(list);

        actionsBtn.setOnMouseClicked(
                e -> popup.show(actionsBtn, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT)
        );

    }

}
