package dk.sdu.swe.domain.controllers;

public class CompaniesController {

    private static CompaniesController instance;

    private CompaniesController() {}

    public static CompaniesController getInstance() {
        if (instance == null) {
            instance = new CompaniesController();
        }
        return instance;
    }

}
