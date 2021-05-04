package dk.sdu.swe.domain.controllers;

public class PersonController {

    private static PersonController instance;

    private PersonController() {}

    public static PersonController getInstance() {
        if (instance == null) {
            instance = new PersonController();
        }
        return instance;
    }

}
