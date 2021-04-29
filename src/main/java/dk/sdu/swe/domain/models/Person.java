package dk.sdu.swe.domain.models;

public class Person {

    private int id;
    private String name;
    private String dateOfBirth;
    private String image;
    private PersonContactDetails contactDetails;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getImage() {
        return image;
    }

    public PersonContactDetails getContactDetails() {
        return contactDetails;
    }



}
