package dk.sdu.swe.domain.models;

import dk.sdu.swe.exceptions.InvalidNameException;
import dk.sdu.swe.exceptions.UserCreationException;

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
    public Name(String name) throws InvalidNameException {
        if (name.trim().split(" ").length != 2) {
            throw new InvalidNameException("Navn skal indeholde både fornavn og efternavn. Ingen mellemnavne tilladt!");
        }

        this.firstName = name.trim().split(" ")[0];
        this.lastName = name.trim().split(" ")[1];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
