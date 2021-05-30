package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.persistence.ICompanyDAO;
import dk.sdu.swe.persistence.DB;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The type Company dao.
 */
public class CompanyDAOImpl extends AbstractDAO<Company> implements ICompanyDAO {

    private static CompanyDAOImpl instance;

    private CompanyDAOImpl() {
        super(Company.class);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public synchronized static ICompanyDAO getInstance() {
        if (instance == null) {
            instance = new CompanyDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Company> search(String searchTerm) {
        searchTerm = '%' + searchTerm + '%';
        String hql = "FROM Company WHERE LOWER(name) LIKE LOWER(:search_term) OR nbr LIKE :search_term";

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
