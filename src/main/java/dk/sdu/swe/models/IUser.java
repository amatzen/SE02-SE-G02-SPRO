package dk.sdu.swe.models;

public interface IUser {
    public boolean hasPermission(String permissionKey);
}
