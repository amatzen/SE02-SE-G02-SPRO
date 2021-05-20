package dk.sdu.swe.persistence.seeders;

import dk.sdu.swe.persistence.DB;
import dk.sdu.swe.domain.models.CreditRole;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The type V 2 1 create credit roles.
 */
public class v2_1_CreateCreditRoles {
    /**
     * Run.
     */
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
