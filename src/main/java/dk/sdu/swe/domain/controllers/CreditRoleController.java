package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CreditRoleDAOImpl;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.persistence.ICreditRoleDAO;

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
        return creditRoleDAO.getAll();
    }

}
