package dk.sdu.swe.data;

import dk.sdu.swe.data.seeders.*;

public class SeederUtility {
    public static void run() {
        try {
            v0_CreateCompanies.run();
            v1_CreateUsers.run();
            v2_CreateChannels.run();
            v3_CreateProgrammesForThisWeek.run();
            v4_CreateCreditRoles.run();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed one or more seeders!");
        }
    }
}
