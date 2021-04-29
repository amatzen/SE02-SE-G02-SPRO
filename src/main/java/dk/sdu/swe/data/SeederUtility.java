package dk.sdu.swe.data;

import dk.sdu.swe.data.seeders.CreateChannels;
import dk.sdu.swe.data.seeders.CreateUsers;

public class SeederUtility {
    public static void run() {
        try {
            CreateUsers.run();
            CreateChannels.run();

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed one or more seeders!");
        }
    }
}
