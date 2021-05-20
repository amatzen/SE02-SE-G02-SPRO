package dk.sdu.swe.persistence;

import dk.sdu.swe.persistence.seeders.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class SeederUtility {
    public static void run() {
        try {
            v0_CreateCompanies.run();
            v1_CreateUsers.run();
            v2_CreateChannels.run();
            v2_1_CreateCreditRoles.run();

            DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                v3_CreateProgrammesForThisWeek.run(ZonedDateTime.now().format(dft), true);
                for (int i = 1; i < 8; i++) {
                    v3_CreateProgrammesForThisWeek.run(ZonedDateTime.now().plusDays(i).format(dft), false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            v4_AddGamerIArbejdstoej.run();
            v5_AddPeopleAndCredits.run();

            runSQLScript("v6_AddEPGProgrammesToProgrammes.sql");

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed one or more seeders!");
        }
    }

    public static void runSQLScript(String sqlFile) {
        Session s = DB.openSession();
        InputStream is = SeederUtility.class.getResourceAsStream("./seeders/"+sqlFile);
        String rs = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining(" "));

        Transaction transaction = s.beginTransaction();

        NativeQuery sqlQuery = s.createSQLQuery(rs);
        sqlQuery.executeUpdate();

        transaction.commit();
        s.close();
    }
}
