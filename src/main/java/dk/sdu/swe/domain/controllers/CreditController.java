package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.FacadeDB;
import dk.sdu.swe.domain.models.Credit;

import java.util.List;

public class CreditController {

    private static CreditController instance;

    private CreditController() {}

    public static CreditController getInstance() {
        if (instance == null) {
            instance = new CreditController();
        }
        return instance;
    }

    public List<Credit> getAll() throws Exception {
        return FacadeDB.getInstance().getCredits();
    }

}
