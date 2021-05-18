package dk.sdu.swe.views.modals.programmes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.data.dao.CreditDAOImpl;
import dk.sdu.swe.data.dao.ReviewDAOImpl;
import dk.sdu.swe.domain.controllers.AuthController;
import dk.sdu.swe.domain.controllers.ChannelController;
import dk.sdu.swe.domain.controllers.CompanyController;
import dk.sdu.swe.domain.controllers.ProgrammeController;
import dk.sdu.swe.domain.models.*;
import dk.sdu.swe.domain.persistence.ICreditDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class ProgrammeModal extends Dialog<Programme> {

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

    private Programme programme;

    public ProgrammeModal(Window window, Programme programme) {
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
                getClass().getClassLoader().getResource("dk/sdu/swe/views/programmes/ProgrammeModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ProgrammeModal(Window window) {
        this(window, null);
    }

    @FXML
    private void initialize() {
        if (programme != null) {
            title.setText(programme.getTitle());
            prodYear.setText(String.valueOf(programme.getProdYear()));
        }

        List<Channel> channelList = ChannelController.getInstance().getAll();
        channel.getToggles().forEach(toggle -> {
            String channelName = ((RadioButton) toggle).getText();
            channelList.stream()
                .filter(channel -> channel.getName().equals(channelName))
                .findFirst()
                .ifPresent(channel -> {
                    toggle.setUserData(channel);
                    if (programme != null && programme.getChannel().getId() == channel.getId()) {
                        toggle.setSelected(true);
                    }
                });
        });

        List<Category> categories = ProgrammeController.getInstance().getCategories();
        categories.forEach(categoryObj -> {
                Label label = new Label(categoryObj.getCategoryTitle());
                label.setUserData(categoryObj);
                category.getItems().add(label);
                if (programme != null && programme.getCategories().stream().map(Category::getId).collect(Collectors.toSet()).contains(categoryObj.getId())) {
                    category.getSelectionModel().select(label);
                }
            }
        );

        CompanyController.getInstance().getAll().forEach(x -> {
            Label l = new Label(x.getName());
            l.setUserData(x);
            prodCompany.getItems().add(l);
        });
        prodCompany.getSelectionModel().selectFirst();
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

        if ( !AuthController.getInstance().getUser().hasPermission("programmes.change.no_review") ) {
            Programme newProgramme = programme.clone();

            newProgramme.setTitle(title);
            newProgramme.setCategories(Set.of(category));
            newProgramme.setProdYear(prodYear);
            newProgramme.setCompany(company);
            newProgramme.setChannel(channel);

            JSONObject original = programme.toJson();
            JSONObject updated = newProgramme.toJson();
            updated.put("credits", programme.getCreditsJson());

            ReviewDAOImpl.getInstance().save(new Review(programme, original, updated));
        } else {
            Programme programme = null;
            if (this.programme == null) {
                programme = ProgrammeController.getInstance()
                    .createProgramme(title, prodYear, channel, Set.of(category), company);
            } else {
                this.programme.setTitle(title);
                this.programme.setCategories(Set.of(category));
                this.programme.setProdYear(prodYear);
                this.programme.setCompany(company);
                this.programme.setChannel(channel);
                programme = this.programme;
            }
        }

        setResult(this.programme);
        hide();
    }

}

