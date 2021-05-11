package dk.sdu.swe.data.seeders;

import dk.sdu.swe.data.dao.CompanyDAOImpl;
import dk.sdu.swe.domain.persistence.IDAO;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.CompanyDetails;

public class v0_CreateCompanies {
    public static void run() throws Exception {
        IDAO<Company> dao = CompanyDAOImpl.getInstance();

        dao.save(new Company("Company1", null,
            new CompanyDetails("Denmark", "Whateva", "YeaYea"), "LogoGoBrr"));

    }
}
