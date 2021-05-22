package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.CreditRole;

import java.util.List;

/**
 * The interface Credit role controller.
 */
public interface ICreditRoleController {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<CreditRole> getAll();

    /**
     * Delete.
     *
     * @param creditRole the credit role
     */
    void delete(CreditRole creditRole);

    /**
     * Create role credit role.
     *
     * @param role the role
     * @return the credit role
     */
    CreditRole createRole(String role);

    /**
     * Update.
     *
     * @param creditRoleObj the credit role obj
     */
    void update(CreditRole creditRoleObj);
}
