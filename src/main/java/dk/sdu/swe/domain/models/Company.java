package dk.sdu.swe.domain.models;

import dk.sdu.swe.data.converters.CompanyDetailsConverter;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(nullable = true, name = "parent_company_id", referencedColumnName = "id")
    private Company parentCompany;

    @Embedded
    private CompanyDetails companyDetails;

    private String logo;

    public Company(String name, Company parentCompany, CompanyDetails companyDetails, String logo) {
        this.name = name;
        this.parentCompany = parentCompany;
        this.companyDetails = companyDetails;
        this.logo = logo;
    }

    public Company() {

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

    public Company getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(Company company) {
        this.parentCompany = company;
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
