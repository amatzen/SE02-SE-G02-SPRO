package dk.sdu.swe.persistence.seeders;

import dk.sdu.swe.persistence.dao.CompanyDAOImpl;
import dk.sdu.swe.domain.persistence.IDAO;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.CompanyDetails;

public class v0_CreateCompanies {
    public static void run() throws Exception {
        IDAO<Company> dao = CompanyDAOImpl.getInstance();

        dao.save(new Company("TV2/DANMARK A/S", null,
            new CompanyDetails("Denmark", "529900PMO5IBS8IO2N04", "10413494"), "https://pbs.twimg.com/profile_images/469398737484144640/o1P1XYg8.png"));

        dao.save(
            new Company(
                "Banijay Group",
                null,
                new CompanyDetails(
                    "France",
                    "969500TVFVKI682L1144",
                    "499797041"
                ),
            "https://screenshots.amatzen.dk/gicyaqEXonuKwOV02M.png"
            )
        );

        dao.save(
            new Company(
                "Nordisk Film TV A/S",
                CompanyDAOImpl.getInstance().getById(2L).orElse(null),
                new CompanyDetails(
                    "Denmark",
                    "",
                    ""
                ),
                "https://screenshots.amatzen.dk/gicyaqEXonuKwOV02M.png"
            )
        );

        dao.save(
            new Company(
                "Pineapple Entertainment ApS",
                CompanyDAOImpl.getInstance().getById(2L).orElse(null),
                new CompanyDetails(
                    "Denmark",
                    "",
                    ""
                ),
                "https://screenshots.amatzen.dk/gicyaqEXonuKwOV02M.png"
            )
        );

        dao.save(
            new Company(
                "Blu A/S",
                null,
                new CompanyDetails(
                    "Denmark",
                    "",
                    "28296819"
                ),
                "https://pbs.twimg.com/profile_images/3211552481/d697214c25469d5b4a2c751c25222cf0_400x400.jpeg"
            )
        );
    }
}
