package dk.sdu.swe.views.modals.programmes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.domain.controllers.AuthController;
import dk.sdu.swe.domain.controllers.ChannelController;
import dk.sdu.swe.domain.controllers.ProgrammeController;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.views.partials.CompanyListItem;
import dk.sdu.swe.views.partials.ProgrammeListItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class AddProgrammesModal extends Dialog<Programme> {

    @FXML
    private JFXButton closeBtn;

    @FXML
    private ToggleGroup channel;

    @FXML
    private TextField title, prodYear;

    @FXML
    private JFXComboBox<Label> category, prodCompany;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private GaussianBlur backgroundEffect;

    public AddProgrammesModal(Window window) {
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
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/programmes/addProgramme.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        List<Channel> channelList = ChannelController.getInstance().getAll();
        channel.getToggles().forEach(toggle -> {
            String channelName = ((RadioButton) toggle).getText();
            channelList.stream()
                .filter(channel -> channel.getName().equals(channelName))
                .findFirst()
                .ifPresent(channel -> toggle.setUserData(channel));
        });

        List<Category> categories = ProgrammeController.getInstance().getCategories();
        categories.forEach(categoryObj -> {
                Label label = new Label(categoryObj.getCategoryTitle());
                label.setUserData(categoryObj);
                category.getItems().add(label);
            }
        );

        Company company = AuthController.getInstance().getUser().getCompany();
        Label label = new Label(company.getName());
        label.setUserData(company);
        prodCompany.getItems().add(label);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        setResult(null);
        hide();
    }

    @FXML
    private void save(ActionEvent event) {
        String title = this.title.getText();
        int prodYear = Integer.parseInt(this.prodYear.getText());
        Channel channel = (Channel) this.channel.getSelectedToggle().getUserData();
        Category category = (Category) this.category.getSelectionModel().getSelectedItem().getUserData();
        Company company = (Company) this.prodCompany.getSelectionModel().getSelectedItem().getUserData();
        Programme programme = ProgrammeController.getInstance().createProgramme(title, prodYear, channel, Set.of(category), company);
        setResult(programme);
        hide();
    }

}

