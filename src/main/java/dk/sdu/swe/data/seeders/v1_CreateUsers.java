package dk.sdu.swe.data.seeders;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.data.dao.CompanyDAOImpl;
import dk.sdu.swe.data.dao.IDAO;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.CompanyAdministrator;
import dk.sdu.swe.domain.models.SystemAdministrator;
import dk.sdu.swe.domain.models.User;
import org.hibernate.Session;

public class v1_CreateUsers {
    public static void run() throws Exception {
        Session session = DB.openSession();

        // Pre-condition
        int users = ((Number) session.createSQLQuery("SELECT COUNT(*) FROM users").getSingleResult()).intValue();
        if(users != 0) {
            return;
        }

        IDAO<Company> companyIDAO = CompanyDAOImpl.getInstance();

        session.saveOrUpdate(new SystemAdministrator("admin", "crms+sysadmin@mgx.dk", "Sys Admin", "kode", companyIDAO.getById(1).get()));
        session.saveOrUpdate(new User("user", "crms+user@mgx.dk", "Normal Bruger", "kode", companyIDAO.getById(1).get()));
        session.saveOrUpdate(new CompanyAdministrator("company", "crms+companyadmin@mgx.dk", "Biz Admin", "kode", companyIDAO.getById(1).get()));

        session.saveOrUpdate(new SystemAdministrator("almat20", "alexander@alexander.dk", "Alexander Matzen", "alexander", companyIDAO.getById(1).get()));

        session.close();
    }
}
