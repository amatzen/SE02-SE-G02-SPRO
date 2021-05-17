package dk.sdu.swe.domain.models;

import org.json.JSONObject;

import javax.persistence.Embeddable;

//@Convert(converter = CompanyDetails.class)
@Embeddable
public class CompanyDetails {

    private String address;
    private String lei;
    private String nbr;

    public CompanyDetails(String address, String lei, String nbr) {
        this.address = address;
        this.lei = lei;
        this.nbr = nbr;
    }

    public CompanyDetails() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String country) {
        this.address = country;
    }

    public String getLei() {
        return lei;
    }

    public void setLei(String lei) {
        this.lei = lei;
    }

    public String getNbr() {
        return nbr;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("lei", getLei());
        json.put("nbr", getNbr());
        json.put("country", getAddress());
        return json;
    }
}
