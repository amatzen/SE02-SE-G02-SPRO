package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Programme;

public class ProgrammeDAO extends AbstractDAO<Programme> {
    private static ProgrammeDAO instance;

    public static ProgrammeDAO getInstance() {
        if (instance == null) {
            instance = new ProgrammeDAO();
        }
        return instance;
    }

    private ProgrammeDAO() {
        super(Programme.class);
    }
}
