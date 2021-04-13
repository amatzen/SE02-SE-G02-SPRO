package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class PopupListMenu extends JFXPopup {

    private JFXListView<Label> listView;
    private Map<Label, Runnable> options;

    public PopupListMenu(Map<String, Runnable> options) {
        listView = new JFXListView<>();
        this.options = new HashMap<>();

        options.forEach((text, runnable) -> {
            Label label = new Label(text);
            this.options.put(label, runnable);
            listView.getItems().add(label);
        });

        setPopupContent(listView);

        listView.setOnMouseClicked(e -> {
            Label label = listView.getSelectionModel().getSelectedItem();
            if (label != null) {
                this.options.get(label).run();
            }
        });
    }

}
