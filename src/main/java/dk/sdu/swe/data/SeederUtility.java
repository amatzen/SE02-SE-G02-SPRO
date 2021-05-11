package dk.sdu.swe.data;

import dk.sdu.swe.data.seeders.v0_CreateCompanies;
import dk.sdu.swe.data.seeders.v2_CreateChannels;
import dk.sdu.swe.data.seeders.v1_CreateUsers;
import dk.sdu.swe.data.seeders.v3_CreateProgrammesForThisWeek;

public class SeederUtility {
    public static void run() {
        try {
            v0_CreateCompanies.run();
            v1_CreateUsers.run();
            v2_CreateChannels.run();
            v3_CreateProgrammesForThisWeek.run();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed one or more seeders!");
        }
    }
}
