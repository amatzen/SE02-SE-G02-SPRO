package dk.sdu.swe.domain.models;

import dk.sdu.swe.domain.controllers.CreditController;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvExport {

    // Test data
    private static List<List<String>> Credits = Arrays.asList(
        Arrays.asList("Biddragyder 1", "Biddragyder 2", "Biddragyder 3")
    );

    // Test data
    private static List<List<String>> Programs = Arrays.asList(
        Arrays.asList("Program 1", "Program 2", "Program 3")
    );

    // Test data
    private static List<List<String>> Companies = Arrays.asList(
        Arrays.asList("Virksomhed 1", "Virksomhed 2", "Virksomhed 3")
    );

    public static void csvExportCredits(File file) {

        List<Credit> credits = CreditController.getInstance().getAll();

        try {
            FileWriter csvWriter = new FileWriter(file);
            csvWriter.append("Biddragydere");
            csvWriter.append(",");
            csvWriter.append("\n");

            for (List<String> data : Credits) {
                csvWriter.append(String.join(",", data));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void csvExportPrograms(File file) {

        try {
            FileWriter cswWriter = new FileWriter(file);
            cswWriter.append("Programmer");
            cswWriter.append(",");
            cswWriter.append("\n");

            for (List<String> data : Programs) {
                cswWriter.append(String.join(",", data));
                cswWriter.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void csvExportCompanies(File file) {
        try {
            FileWriter cswWriter = new FileWriter(file);
            cswWriter.append("Virksomheder");
            cswWriter.append(",");
            cswWriter.append("\n");

            for (List<String> data : Companies) {
                cswWriter.append(String.join(",", data));
                cswWriter.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
