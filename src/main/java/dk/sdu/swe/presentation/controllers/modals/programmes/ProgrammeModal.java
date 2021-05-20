package dk.sdu.swe.presentation.controllers.modals.programmes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dk.sdu.swe.cross_cutting.helpers.PubSub;
import dk.sdu.swe.domain.controllers.*;
import dk.sdu.swe.domain.models.*;
import dk.sdu.swe.presentation.AlertHelper;
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
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * The type Programme modal.
 */
public class ProgrammeModal extends Dialog<Programme> {

    @FXML
    private JFXButton closeBtn;

    @FXML
    private ToggleGroup channel;

    @FXML
    private TextField title, prodYear;

    @FXML
    private Label modalTitle;

    @FXML
    private JFXComboBox<Label> category, prodCompany;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private GaussianBlur backgroundEffect;

    private Programme programme;

    /**
     * Instantiates a new Programme modal.
     *
     * @param window    the window
     * @param programme the programme
     */
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
                getClass().getClassLoader().getResource("dk/sdu/swe/presentation/views/programmes/ProgrammeModal.fxml")));
        fxmlLoader.setController(this);

        try {
            getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a new Programme modal.
     *
     * @param window the window
     */
    public ProgrammeModal(Window window) {
        this(window, null);
    }

    @FXML
    private void initialize() {
        if (programme != null) {
            modalTitle.setText("Redigér program");
            title.setText(programme.getTitle());
            prodYear.setText(String.valueOf(programme.getProdYear()));
        } else {
            modalTitle.setText("Tilføj program");
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

        CompanyController.getInstance().getAll().forEach(company -> {
            Label companyLbl = new Label(company.getName());
            companyLbl.setUserData(company);
            prodCompany.getItems().add(companyLbl);
            if (programme != null &&
                programme.getCompany() != null
                && company.getId().equals(programme.getCompany().getId())) {
                prodCompany.getSelectionModel().select(companyLbl);
            }
        });
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
        int prodYear = 0;
        try {
             prodYear = Integer.parseInt(this.prodYear.getText());
        } catch (NumberFormatException e) {
            AlertHelper.show(Alert.AlertType.ERROR,
                getOwner(), "Ikke et tal",
                "Produktionsåret skal være et tal.");
            return;
        }

        Channel channel = null;
        if (this.channel.getSelectedToggle() != null) {
            channel = (Channel) this.channel.getSelectedToggle().getUserData();
        }

        Category category = null;
        if (this.category.getSelectionModel().getSelectedItem() != null) {
            category = (Category) this.category.getSelectionModel().getSelectedItem().getUserData();
        }

        Company company = null;
        if (this.prodCompany.getSelectionModel().getSelectedItem() != null) {
            company = (Company) this.prodCompany.getSelectionModel().getSelectedItem().getUserData();
        }

        if (!AuthController.getInstance().getUser().hasPermission("programmes.change.no_review")) {
            JSONObject updated = new JSONObject();
            boolean existingProgramme = true;

            if (Objects.nonNull(programme)) {
                existingProgramme = false;
                Programme newProgramme = programme.clone();

                newProgramme.setTitle(title);
                newProgramme.setCategories(Set.of(category));
                newProgramme.setProdYear(prodYear);
                newProgramme.setCompany(company);
                newProgramme.setChannel(channel);
                updated = newProgramme.toJson();

            }

            if(!existingProgramme) {
                programme = new Programme(title, channel, prodYear, Set.of(category), company);
            }

            updated.put("credits", programme.getCreditsJson());
            JSONObject original = programme.toJson();
            programme = null;

            ReviewController.getInstance().save(new Review(programme, original, updated));

            setResult(null);
        } else {
            Programme programme;
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
            setResult(programme);
        }

        PubSub.publish("trigger_update:programmes:refresh", true);
        hide();
    }
}

