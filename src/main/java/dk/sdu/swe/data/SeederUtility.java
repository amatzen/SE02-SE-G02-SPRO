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

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed one or more seeders!");
        }
    }
}
