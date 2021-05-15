package dk.sdu.swe.views.modals.programmes;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;


public class AddProgrammesModal extends Dialog <Boolean> {

    @FXML
    private JFXButton closeBtn;

    @FXML
    private ToggleGroup channel;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private GaussianBlur backgroundEffect;

    public AddProgrammesModal(Window window) {
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
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/programmes/addProgramme.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void handleClose(ActionEvent event) {
        setResult(false);
        hide();
    }

}

