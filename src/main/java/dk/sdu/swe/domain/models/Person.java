package dk.sdu.swe.domain.models;

import com.google.gson.annotations.SerializedName;
import dk.sdu.swe.data.FacadeDB;

import java.util.List;

public class Person {

    private int id;
    private String name;
    @SerializedName("dob")
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

    public static List<Person> getAll() throws Exception {
        return FacadeDB.getInstance().getPeople();
    }

    public static Person get(int id) throws Exception {
        return FacadeDB.getInstance().getPerson(id);
    }

}
