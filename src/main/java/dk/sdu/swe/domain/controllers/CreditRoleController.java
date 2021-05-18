package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CreditRoleDAOImpl;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.ICreditRoleDAO;

import java.util.Comparator;
import java.util.List;

public class CreditRoleController {

    private ICreditRoleDAO creditRoleDAO;

    private static CreditRoleController instance;

    public static CreditRoleController getInstance() {
        if (instance == null) {
            instance = new CreditRoleController();
        }
        return instance;
    }

    private CreditRoleController() {
        creditRoleDAO = CreditRoleDAOImpl.getInstance();
    }

    public List<CreditRole> getAll() {
        List<CreditRole> creditRoles = creditRoleDAO.getAll();
        creditRoles.sort(Comparator.comparing(CreditRole::getTitle));
        return creditRoles;
    }

    public void delete(CreditRole creditRole) {
        creditRoleDAO.delete(creditRole);
    }

    public CreditRole createRole(String role) {
        CreditRole creditRole = new CreditRole(role);
        creditRoleDAO.save(creditRole);
        return creditRole;
    }

    public void update(CreditRole creditRoleObj) {
        creditRoleDAO.update(creditRoleObj);
    }
}
