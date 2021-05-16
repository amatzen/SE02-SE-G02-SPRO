package dk.sdu.swe.domain.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(nullable = true, name = "parent_company_id", referencedColumnName = "id")
    private Company parentCompany;

    @Embedded
    private CompanyDetails companyDetails;

    private String logo;

    @OneToMany(mappedBy = "company")
    private List<User> users;

    public Company(String name, Company parentCompany, CompanyDetails companyDetails, String logo) {
        users = new LinkedList<>();
        this.name = name;
        this.parentCompany = parentCompany;
        this.companyDetails = companyDetails;
        this.logo = logo;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
