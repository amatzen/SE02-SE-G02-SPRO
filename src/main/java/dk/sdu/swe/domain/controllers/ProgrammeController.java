package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Programme;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public List<Programme> getAll() {

        Session session = DB.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Programme> criteriaQuery = criteriaBuilder.createQuery(Programme.class);
        Root<Programme> programmeRoot = criteriaQuery.from(Programme.class);
        criteriaQuery.select(programmeRoot);

        List<Programme> res = session.createQuery(criteriaQuery).getResultList();

        session.close();

        return res;
    }

}
