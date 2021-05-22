package dk.sdu.swe.domain.models;

import org.json.JSONObject;

import javax.persistence.Embeddable;

/**
 * The type Company details.
 */
//@Convert(converter = CompanyDetails.class)
@Embeddable
public class CompanyDetails {

    private String address;
    private String lei;
    private String nbr;

    /**
     * Instantiates a new Company details.
     *
     * @param address the address
     * @param lei     the lei
     * @param nbr     the nbr
     */
    public CompanyDetails(String address, String lei, String nbr) {
        this.address = address;
        this.lei = lei;
        this.nbr = nbr;
    }

    /**
     * Instantiates a new Company details.
     */
    public CompanyDetails() {

    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param country the country
     */
    public void setAddress(String country) {
        this.address = country;
    }

    /**
     * Gets lei.
     *
     * @return the lei
     */
    public String getLei() {
        return lei;
    }

    /**
     * Sets lei.
     *
     * @param lei the lei
     */
    public void setLei(String lei) {
        this.lei = lei;
    }

    /**
     * Gets nbr.
     *
     * @return the nbr
     */
    public String getNbr() {
        return nbr;
    }

    /**
     * Sets nbr.
     *
     * @param nbr the nbr
     */
    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    /**
     * To json json object.
     *
     * @return the json object
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("lei", getLei());
        json.put("nbr", getNbr());
        json.put("country", getAddress());
        return json;
    }
}
