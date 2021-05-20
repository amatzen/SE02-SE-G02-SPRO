package dk.sdu.swe.domain.models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Company.
 */
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

    /**
     * Instantiates a new Company.
     *
     * @param name           the name
     * @param parentCompany  the parent company
     * @param companyDetails the company details
     * @param logo           the logo
     */
    public Company(String name, Company parentCompany, CompanyDetails companyDetails, String logo) {
        users = new LinkedList<>();
        this.name = name;
        this.parentCompany = parentCompany;
        this.companyDetails = companyDetails;
        this.logo = logo;
    }

    /**
     * Instantiates a new Company.
     */
    public Company() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets parent company.
     *
     * @return the parent company
     */
    public Company getParentCompany() {
        return parentCompany;
    }

    /**
     * Sets parent company.
     *
     * @param company the company
     */
    public void setParentCompany(Company company) {
        this.parentCompany = company;
    }

    /**
     * Gets company details.
     *
     * @return the company details
     */
    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    /**
     * Sets company details.
     *
     * @param companyDetails the company details
     */
    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    /**
     * Gets logo.
     *
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets logo.
     *
     * @param logo the logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
