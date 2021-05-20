package dk.sdu.swe.domain.models;

public interface IUser {
    boolean hasPermission(String permissionKey);
}
