package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompanyListItem extends VBox {

    private Map<String, Runnable> options = new HashMap<>() {{
        put("Rediger", CompanyListItem.this::testAction1);
        put("Slet", CompanyListItem.this::testAction2);
        put("T1", CompanyListItem.this::testAction1);
        put("T2", CompanyListItem.this::testAction2);
    }};


    @FXML
    private JFXButton actionsBtn;

    @FXML
    private Label cvrLabel;

    @FXML
    private Label ceoLabel;

    @FXML
    private Label adminLabel;

    public CompanyListItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("dk/sdu/swe/ui/companies/components/CompanyListItem.fxml")));
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
        PopupListMenu popupList = new PopupListMenu(options);

        actionsBtn.setOnMouseClicked(e -> {
            popupList.show(actionsBtn, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        });
    }

    private void testAction1() {
        System.out.println("Test1");
    }

    private void testAction2() {
        System.out.println("Test2");
    }

}
