package dk.sdu.swe.domain.controllers;

public class CreditController {

    private static CreditController instance;

    private CreditController() {}

    public static CreditController getInstance() {
        if (instance == null) {
            instance = new CreditController();
        }
        return instance;
    }

}
