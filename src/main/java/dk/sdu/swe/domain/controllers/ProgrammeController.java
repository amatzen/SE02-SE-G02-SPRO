package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.FacadeDB;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;

public class ProgrammeController {

    private static ProgrammeController instance;

    private ProgrammeController() {}

    public static ProgrammeController getInstance() {
        if (instance == null) {
            instance = new ProgrammeController();
        }
        return instance;
    }

    public static List<Programme> getAll() throws Exception {
        return FacadeDB.getInstance().getProgrammes();
    }

}
