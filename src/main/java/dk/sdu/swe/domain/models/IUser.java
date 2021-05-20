package dk.sdu.swe.domain.models;

/**
 * The interface User.
 */
public interface IUser {
    /**
     * Has permission boolean.
     *
     * @param permissionKey the permission key
     * @return the boolean
     */
    boolean hasPermission(String permissionKey);
}
