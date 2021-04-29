package dk.sdu.swe.data;

import dk.sdu.swe.domain.models.*;
import dk.sdu.swe.helpers.EnvironmentSelector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DB {
    private volatile static SessionFactory sessionFactory;
    private volatile static List<Class> annotatedClasses = new ArrayList<>();
    public synchronized static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            dk.sdu.swe.helpers.Environment env = EnvironmentSelector.getInstance().getEnvironment();
            String DB_HOST = System.getenv("DATABASE_LOCAL_HOST");
            String DB_NAME = System.getenv("DATABASE_LOCAL_DB");
            String DB_USER = System.getenv("DATABASE_LOCAL_USER");
            String DB_PASS = System.getenv("DATABASE_LOCAL_PASS");
            String HIBERNATE_DDL = "create-drop";

            if(env == dk.sdu.swe.helpers.Environment.PROD) {
                DB_HOST = System.getenv("DATABASE_PROD_HOST");
                DB_NAME = System.getenv("DATABASE_PROD_DB");
                DB_USER = System.getenv("DATABASE_PROD_USER");
                DB_PASS = System.getenv("DATABASE_PROD_HOST");
                HIBERNATE_DDL = "update";
            }

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
            settings.put(Environment.SHOW_SQL, true);
            settings.put(Environment.HBM2DDL_AUTO, HIBERNATE_DDL);

            System.out.println(settings.toString());


            cfg.setProperties(settings);

            annotatedClasses.forEach(cfg::addAnnotatedClass);
            sessionFactory = cfg.buildSessionFactory();
        }

        return sessionFactory;
    }

    public synchronized static void addAnnotatedClasses() {
        annotatedClasses.add(User.class);
        annotatedClasses.add(SystemAdministrator.class);
        annotatedClasses.add(CompanyAdministrator.class);

        annotatedClasses.add(Programme.class);
        annotatedClasses.add(Credit.class);
        annotatedClasses.add(Person.class);
        annotatedClasses.add(Channel.class);
    }

    public synchronized static Session openSession() {
        return getSessionFactory().openSession();
    }
}
