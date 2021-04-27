package dk.sdu.swe.domain.controllers;

public class UsersController {

    private static UsersController instance;

    private UsersController() {};

    public static UsersController getInstance() {
        if (instance == null) {
            instance = new UsersController();
        }
        return instance;
    }

}
