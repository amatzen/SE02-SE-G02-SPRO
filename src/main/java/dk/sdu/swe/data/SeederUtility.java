package dk.sdu.swe.data;

import dk.sdu.swe.data.seeders.*;
import org.joda.time.DateTime;

public class SeederUtility {
    public static void run() {
        try {
            v0_CreateCompanies.run();
            v1_CreateUsers.run();
            v2_CreateChannels.run();

            new Thread(() -> {
                try {
                    v3_CreateProgrammesForThisWeek.run(DateTime.now().toString("yyyy-MM-dd"));
                    for (int i = 1; i < 8; i++) {
                        v3_CreateProgrammesForThisWeek.run(DateTime.now().plusDays(i).toString("yyyy-MM-dd"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            v2_1_CreateCreditRoles.run();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed one or more seeders!");
        }
    }
}
