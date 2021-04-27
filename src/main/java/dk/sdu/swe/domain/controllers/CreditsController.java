package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.FacadeDB;
import dk.sdu.swe.domain.models.Credit;

import java.util.List;

public class CreditsController {

    private static CreditsController instance;

    private CreditsController() {}

    public static CreditsController getInstance() {
        if (instance == null) {
            instance = new CreditsController();
        }
        return instance;
    }

    public List<Credit> getAll() throws Exception {
        return FacadeDB.getInstance().getCredits();
    }

}
