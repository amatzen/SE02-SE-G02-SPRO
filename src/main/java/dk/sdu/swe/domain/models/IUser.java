package dk.sdu.swe.domain.models;

public interface IUser {
    public boolean hasPermission(String permissionKey);
}
