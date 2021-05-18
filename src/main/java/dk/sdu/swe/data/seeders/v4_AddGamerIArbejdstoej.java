package dk.sdu.swe.data.seeders;

import dk.sdu.swe.domain.controllers.PersonController;

import java.time.ZonedDateTime;

public class v4_AddGamerIArbejdstoej {
    public static void run() {
        PersonController.getInstance().createPerson("Ashvikan Sivabalaa", "https://mirror.alexander.dk/gia.png", "ashvikan10@gmail.com", ZonedDateTime.parse("2000-06-18T00:00:00Z"));
    }
}
