package dk.sdu.swe.data;

import dk.sdu.swe.data.seeders.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SeederUtility {
    public static void run() {
        try {
            v0_CreateCompanies.run();
            v1_CreateUsers.run();
            v2_CreateChannels.run();

            DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            new Thread(() -> {
                try {
                    v3_CreateProgrammesForThisWeek.run(ZonedDateTime.now().format(dft));
                    for (int i = 1; i < 8; i++) {
                        v3_CreateProgrammesForThisWeek.run(ZonedDateTime.now().plusDays(i).format(dft));
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
