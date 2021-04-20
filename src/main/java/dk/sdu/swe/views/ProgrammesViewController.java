package dk.sdu.swe.views;

import dk.sdu.swe.models.Programme;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ProgrammesViewController extends BorderPane {

    public ProgrammesViewController() {

        final ArrayList<String> channels = new ArrayList<String>();
        final ArrayList<String> category = new ArrayList<String>();

        Programme Test = new Programme(1,"Gutterne På Kutterne","TV2",2021,"Komedie");
        Programme Test2 = new Programme(2,"Gutterne På Kutterne 2", "TV2",2021,"Komedie");

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/programmes/ListView.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
