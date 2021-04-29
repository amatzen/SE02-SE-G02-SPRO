package dk.sdu.swe.domain.controllers;

public class CompanyController {

    private static CompanyController instance;

    private CompanyController() {}

    public static CompanyController getInstance() {
        if (instance == null) {
            instance = new CompanyController();
        }
        return instance;
    }

}
