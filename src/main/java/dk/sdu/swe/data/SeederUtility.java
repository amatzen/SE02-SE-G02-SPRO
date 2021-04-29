package dk.sdu.swe.data;

import dk.sdu.swe.data.seeders.v2_CreateChannels;
import dk.sdu.swe.data.seeders.v1_CreateUsers;

public class SeederUtility {
    public static void run() {
        try {
            v1_CreateUsers.run();
            v2_CreateChannels.run();

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed one or more seeders!");
        }
    }
}
