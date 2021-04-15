package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class PopupListMenu extends JFXPopup {

    private JFXListView<Label> listView;
    private Map<Label, Runnable> actions;

    public PopupListMenu(Map<String, Runnable> actions) {
        listView = new JFXListView<>();
        this.actions = new HashMap<>();

        actions.forEach((text, runnable) -> {
            Label label = new Label(text);
            this.actions.put(label, runnable);
            listView.getItems().add(label);
        });

        setPopupContent(listView);

        listView.setOnMouseClicked(e -> {
            Label label = listView.getSelectionModel().getSelectedItem();
            if (label != null) {
                this.hide();
                this.actions.get(label).run();
            }
        });
        listView.getStylesheets().add("styles/popup.css");
    }

}
