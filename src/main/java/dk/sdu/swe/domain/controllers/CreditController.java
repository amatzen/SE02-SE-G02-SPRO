package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.controllers.contracts.ICreditController;
import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.persistence.ICreditDAO;
import dk.sdu.swe.persistence.dao.CreditDAOImpl;

import java.util.List;

/**
 * The type Credit controller.
 */
public class CreditController implements ICreditController {

    private static ICreditController instance;
    private final ICreditDAO creditDAO;

    private CreditController() {
        creditDAO = CreditDAOImpl.getInstance();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ICreditController getInstance() {
        if (instance == null) {
            instance = new CreditController();
        }
        return instance;
    }

    @Override
    public Credit createCredit(Person person, CreditRole creditRole, Programme programme) {
        Credit credit = new Credit(person, creditRole);
        credit.setProgramme(programme);
        programme.getCredits().add(credit);
        creditDAO.save(credit);
        ProgrammeController.getInstance().updateProgramme(programme);
        return credit;
    }

    @Override
    public void update(Credit creditObj) {
        creditDAO.update(creditObj);
    }

    @Override
    public void delete(Credit credit) {
        Programme programme = credit.getProgramme();
        programme.getCredits().remove(credit);
        creditDAO.delete(credit);
        ProgrammeController.getInstance().updateProgramme(programme);
    }

    @Override
    public List<Credit> getAll() {
        return creditDAO.getAll();
    }
}
