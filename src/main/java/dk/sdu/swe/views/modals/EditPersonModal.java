package dk.sdu.swe.views.modals;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.Objects;

public class EditPersonModal extends Dialog<Boolean> {

    @FXML
    private TextField rolesTextfield;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField seriesTextField;

    @FXML
    private ImageView imageview;

    @FXML
    private JFXButton saveBtn, closeBtn;

    private GaussianBlur backgroundEffect;

    public EditPersonModal(Window window) {
        this.initOwner(window);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);

        backgroundEffect = new GaussianBlur(10);
        window.getScene().getRoot().setEffect(backgroundEffect);

        setOnCloseRequest((event) -> {
            getOwner().getScene().getRoot().setEffect(null);
        });

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/person/EditPerson.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleClose(ActionEvent event) {
        setResult(false);
        hide();
    }
}
