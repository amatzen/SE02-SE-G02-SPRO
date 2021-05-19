package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.CreditRole;

import java.util.List;

public interface ICreditRoleController {
    List<CreditRole> getAll();

    void delete(CreditRole creditRole);

    CreditRole createRole(String role);

    void update(CreditRole creditRoleObj);
}
