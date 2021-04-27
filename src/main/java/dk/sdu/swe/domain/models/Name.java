package dk.sdu.swe.domain.models;

import dk.sdu.swe.exceptions.InvalidNameException;

/**
 * Name
 */
public class Name {
    /**
     * The First name.
     */
    protected String firstName;
    /**
     * The Last name.
     */
    protected String lastName;

    /**
     * Instantiates a new Name.
     *
     * @param firstName user's first name
     * @param lastName  user's last name
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Instantiates a new Name.
     *
     * @param name the name
     * @throws Exception the exception
     */
    public Name(String name) throws Exception {
        if (name.trim().split(" ").length != 2) {
            throw new InvalidNameException("Name must contain both first and last name. No middle names allowed!");
        }

        this.firstName = name.trim().split(" ")[0];
        this.lastName = name.trim().split(" ")[1];
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
