package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.persistence.DB;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.persistence.ICompanyDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyDAOImpl extends AbstractDAO<Company> implements ICompanyDAO {

    private static CompanyDAOImpl instance;

    public synchronized static ICompanyDAO getInstance() {
        if (instance == null) {
            instance = new CompanyDAOImpl();
        }
        return instance;
    }

    private CompanyDAOImpl() {
        super(Company.class);
    }

    @Override
    public List<Company> search(String searchTerm) {
        searchTerm = '%' + searchTerm + '%';
        String hql = "FROM Company WHERE name LIKE :search_term OR nbr LIKE :search_term";

        Session session = DB.openSession();
        Transaction trans = session.beginTransaction();

        List<Company> companyList = null;

        try {
            Query query = session.createQuery(hql);
            query.setParameter("search_term", searchTerm);
            companyList = query.list();
        } finally {
            trans.commit();
            session.close();
        }

        return companyList;
    }
}
