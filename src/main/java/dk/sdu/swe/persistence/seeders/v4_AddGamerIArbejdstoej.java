package dk.sdu.swe.persistence.seeders;

import dk.sdu.swe.domain.controllers.PersonController;
import dk.sdu.swe.cross_cutting.exceptions.PersonCreationException;

import java.time.ZonedDateTime;

public class v4_AddGamerIArbejdstoej {
    public static void run() {
        try {
            PersonController.getInstance().createPerson("Ashvikan Sivabalaa", "https://mirror.alexander.dk/gia.png", "ashvikan10@gmail.com", ZonedDateTime.parse("2000-06-18T00:00:00Z"));
        } catch (PersonCreationException e) {
            e.printStackTrace();
        }
    }
}
