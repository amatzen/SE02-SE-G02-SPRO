package dk.sdu.swe.persistence.seeders;

import dk.sdu.swe.cross_cutting.exceptions.PersonCreationException;
import dk.sdu.swe.domain.controllers.PersonController;

import java.time.ZonedDateTime;

/**
 * The type V 4 add gamer i arbejdstoej.
 */
public class v4_AddGamerIArbejdstoej {
    /**
     * Run.
     */
    public static void run() {
        try {
            PersonController.getInstance().createPerson("Ashvikan Sivabalaa", "https://mirror.alexander.dk/gia.png", "ashvikan10@gmail.com", ZonedDateTime.parse("2000-06-18T00:00:00Z"));
        } catch (PersonCreationException e) {
            e.printStackTrace();
        }
    }
}
