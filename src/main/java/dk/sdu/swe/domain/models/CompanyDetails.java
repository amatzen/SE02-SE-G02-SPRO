package dk.sdu.swe.domain.models;

public class CompanyDetails {

    private String country;
    private String lei;
    private String nbr;

    public CompanyDetails(String country, String lei, String nbr) {
        this.country = country;
        this.lei = lei;
        this.nbr = nbr;
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
}
