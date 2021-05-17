package dk.sdu.swe.data.seeders;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.data.dao.CreditRoleDAOImpl;
import dk.sdu.swe.domain.models.CreditRole;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class v2_1_CreateCreditRoles {
    public static void run() {
        Session session = DB.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(new CreditRole("Skuespiller"));
        session.save(new CreditRole("Instruktør"));
        session.save(new CreditRole("Klipper"));
        session.save(new CreditRole("Fotograf"));
        session.save(new CreditRole("Stylist"));
        session.save(new CreditRole("Casting"));
        session.save(new CreditRole("Makeup-artist"));
        session.save(new CreditRole("Alt muligt mand"));

        transaction.commit();
        session.close();
    }
}
