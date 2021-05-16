package dk.sdu.swe.views.modals;

import com.jfoenix.controls.JFXButton;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.views.partials.CreditListItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CreditListModal extends Dialog<Boolean> {

    @FXML
    private JFXButton closeBtn;

    @FXML
    private GaussianBlur backgroundEffect;

    @FXML
    private Label titleLbl, categoryLbl;

    @FXML
    private FlowPane creditsPane;

    private Programme programme;

    public CreditListModal(Window window, Programme programme) {
        this.programme = programme;

        this.setResultConverter(param -> null);
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
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/credits/CreditList.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {

        titleLbl.setText(programme.getTitle());
        categoryLbl.setText(programme.getCategories().stream()
            .map(Category::getCategoryTitle)
            .collect(Collectors.joining(", ")));

        List<Credit> credits = null;

        credits = programme.getCredits();

        for (Credit credit : credits) {
            creditsPane.getChildren().add(new CreditListItem(credit));
        }
    }

    @FXML
    private void handleClose(ActionEvent event) {
        setResult(false);
        hide();
    }

    @FXML
    private void addCreditBtn(ActionEvent event) {
        Dialog editDialog = new AddCreditModal(getDialogPane().getScene().getWindow());
        editDialog.show();
    }
}
