package dk.sdu.swe.domain.models;

public class Company {
    private int id;
    private String name;
    private int subsidiaryOf;
    private CompanyDetails companyDetails;
    private String logo;

    public Company(int id, String name, int subsidiaryOf, CompanyDetails companyDetails, String logo) {
        this.id = id;
        this.name = name;
        this.subsidiaryOf = subsidiaryOf;
        this.companyDetails = companyDetails;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubsidiaryOf() {
        return subsidiaryOf;
    }

    public void setSubsidiaryOf(int subsidiaryOf) {
        this.subsidiaryOf = subsidiaryOf;
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
