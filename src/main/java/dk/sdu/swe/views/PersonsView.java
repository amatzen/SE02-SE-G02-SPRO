package dk.sdu.swe.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class PersonsView extends VBox {

    public PersonsView() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("dk/sdu/swe/ui/persons/persons.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
