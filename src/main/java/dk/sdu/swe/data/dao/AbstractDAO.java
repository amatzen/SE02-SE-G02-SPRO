package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.persistence.IDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> implements IDAO<T> {

    private Class<T> type;

    public AbstractDAO(Class<T> tClass) {
        this.type = tClass;
    }

    @Override
    public List getAll() {
        Session session = DB.openSession();

        List<T> res = null;

        try {
            Transaction trans = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
            Root<T> programmeRoot = criteriaQuery.from(type);
            criteriaQuery.select(programmeRoot);

            res = session.createQuery(criteriaQuery).getResultList();

            trans.commit();
        } finally {
            session.close();
        }

        return res;
    }

    @Override
    public Optional<T> getById(Long obj) {
        Session session = DB.openSession();

        T res = null;

        try {
            Transaction trans = session.beginTransaction();

            res = session.get(type, obj);

            trans.commit();
        } finally {
            session.close();
        }

        return Optional.ofNullable(res);
    }

    @Override
    public void save(T obj) {
        Session session = DB.openSession();
        try {
            Transaction trans = session.beginTransaction();
            session.save(obj);
            trans.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(T obj) {
        Session session = DB.openSession();
        try {
            Transaction trans = session.beginTransaction();
            session.update(obj);
            trans.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(T obj) {
        Session session = DB.openSession();
        try {
            session.getTransaction().begin();
            session.delete(obj);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
