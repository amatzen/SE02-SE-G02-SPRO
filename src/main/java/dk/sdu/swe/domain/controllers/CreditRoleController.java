package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.controllers.contracts.ICreditRoleController;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.persistence.ICreditRoleDAO;
import dk.sdu.swe.persistence.dao.CreditRoleDAOImpl;

import java.util.Comparator;
import java.util.List;

/**
 * The type Credit role controller.
 */
public class CreditRoleController implements ICreditRoleController {

    private ICreditRoleDAO creditRoleDAO;

    private static ICreditRoleController instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ICreditRoleController getInstance() {
        if (instance == null) {
            instance = new CreditRoleController();
        }
        return instance;
    }

    private CreditRoleController() {
        creditRoleDAO = CreditRoleDAOImpl.getInstance();
    }

    @Override
    public List<CreditRole> getAll() {
        List<CreditRole> creditRoles = creditRoleDAO.getAll();
        creditRoles.sort(Comparator.comparing(CreditRole::getTitle));
        return creditRoles;
    }

    @Override
    public void delete(CreditRole creditRole) {
        creditRoleDAO.delete(creditRole);
    }

    @Override
    public CreditRole createRole(String role) {
        CreditRole creditRole = new CreditRole(role);
        creditRoleDAO.save(creditRole);
        return creditRole;
    }

    @Override
    public void update(CreditRole creditRoleObj) {
        creditRoleDAO.update(creditRoleObj);
    }
}
