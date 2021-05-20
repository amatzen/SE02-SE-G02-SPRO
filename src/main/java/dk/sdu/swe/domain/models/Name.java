package dk.sdu.swe.domain.models;

import dk.sdu.swe.cross_cutting.exceptions.InvalidNameException;

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
     * @throws InvalidNameException the invalid name exception
     */
    public Name(String name) throws InvalidNameException {
        if (name.trim().split(" ").length != 2) {
            throw new InvalidNameException("Navn skal indeholde både fornavn og efternavn. Ingen mellemnavne tilladt!");
        }

        this.firstName = name.trim().split(" ")[0];
        this.lastName = name.trim().split(" ")[1];
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
