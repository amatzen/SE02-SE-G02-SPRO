package dk.sdu.swe.persistence;

import dk.sdu.swe.cross_cutting.helpers.EnvironmentSelector;
import dk.sdu.swe.domain.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Db.
 */
public class DB {
    private volatile static SessionFactory sessionFactory;
    private volatile static List<Class> annotatedClasses = new ArrayList<>();

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public synchronized static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            dk.sdu.swe.cross_cutting.helpers.Environment env = EnvironmentSelector.getInstance().getEnvironment();
            String DB_HOST = System.getenv("DATABASE_LOCAL_HOST");
            String DB_NAME = System.getenv("DATABASE_LOCAL_DB");
            String DB_USER = System.getenv("DATABASE_LOCAL_USER");
            String DB_PASS = System.getenv("DATABASE_LOCAL_PASS");
            String HIBERNATE_DDL = "create-drop";

            if(env == dk.sdu.swe.cross_cutting.helpers.Environment.PROD) {
                DB_HOST = System.getenv("DATABASE_PROD_HOST");
                DB_NAME = System.getenv("DATABASE_PROD_DB");
                DB_USER = System.getenv("DATABASE_PROD_USER");
                DB_PASS = System.getenv("DATABASE_PROD_PASS");
                HIBERNATE_DDL = "update";
            }

            System.out.println("[DB] Selected Environment: " + env.getLabel());

            addAnnotatedClasses();

            Configuration cfg = new Configuration();
            Properties settings = new Properties();

            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, String.format(
                "jdbc:postgresql://%s/%s?useSSL=false",
                DB_HOST,
                DB_NAME
            ));
            settings.put(Environment.USER, DB_USER);
            settings.put(Environment.PASS, DB_PASS);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.SHOW_SQL, false);
            settings.put(Environment.PHYSICAL_NAMING_STRATEGY, "dk.sdu.swe.persistence.strategies.NameStrategy");

            settings.put("hibernate.connection.CharSet", "utf8");
            settings.put("hibernate.connection.characterEncoding", "utf8");
            settings.put("hibernate.connection.useUnicode", true);

            settings.put(Environment.HBM2DDL_AUTO, HIBERNATE_DDL);

            settings.put(Environment.USE_QUERY_CACHE, true);
            settings.put(Environment.USE_SECOND_LEVEL_CACHE, true);
            settings.put(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.EhCacheRegionFactory");
            settings.put("hibernate.enable_lazy_load_no_trans", true);

            cfg.setProperties(settings);

            annotatedClasses.forEach(cfg::addAnnotatedClass);
            sessionFactory = cfg.buildSessionFactory();
        }

        return sessionFactory;
    }

    /**
     * Reset session factory.
     */
    public synchronized static void resetSessionFactory() {
        sessionFactory = null;
        getSessionFactory();
    }

    /**
     * Add annotated classes.
     */
    public synchronized static void addAnnotatedClasses() {
        annotatedClasses.add(User.class);
        annotatedClasses.add(SystemAdministrator.class);
        annotatedClasses.add(CompanyAdministrator.class);
        annotatedClasses.add(Programme.class);
        annotatedClasses.add(Credit.class);
        annotatedClasses.add(CreditRole.class);
        annotatedClasses.add(Person.class);
        annotatedClasses.add(Channel.class);
        annotatedClasses.add(EPGProgramme.class);
        annotatedClasses.add(Category.class);
        annotatedClasses.add(Company.class);
        annotatedClasses.add(Review.class);
    }

    /**
     * Open session session.
     *
     * @return the session
     */
    public synchronized static Session openSession() {
        return getSessionFactory().openSession();
    }

    /**
     * Load all data list.
     *
     * @param <T>     the type parameter
     * @param type    the type
     * @param session the session
     * @return the list
     */
    public static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
