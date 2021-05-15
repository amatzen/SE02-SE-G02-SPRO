package dk.sdu.swe.data.seeders;

import dk.sdu.swe.data.dao.CompanyDAOImpl;
import dk.sdu.swe.domain.persistence.IDAO;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.CompanyDetails;

public class v0_CreateCompanies {
    public static void run() throws Exception {
        IDAO<Company> dao = CompanyDAOImpl.getInstance();

        dao.save(new Company("TV2/DANMARK A/S", null,
            new CompanyDetails("Denmark", "529900PMO5IBS8IO2N04", "10413494"), "https://pbs.twimg.com/profile_images/469398737484144640/o1P1XYg8.png"));

    }
}
