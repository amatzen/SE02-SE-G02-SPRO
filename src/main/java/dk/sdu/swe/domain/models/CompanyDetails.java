package dk.sdu.swe.domain.models;

import org.json.JSONObject;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

//@Convert(converter = CompanyDetails.class)
@Embeddable
public class CompanyDetails {

    private String country;
    private String lei;
    private String nbr;

    public CompanyDetails(String country, String lei, String nbr) {
        this.country = country;
        this.lei = lei;
        this.nbr = nbr;
    }

    public CompanyDetails() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        json.put("country", getCountry());
        return json;
    }
}
